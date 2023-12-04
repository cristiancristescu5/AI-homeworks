import numpy as np
import matplotlib.pyplot as plt


class WindyGridworldQlearning:
    def __init__(self, num_rows, num_cols, start_state, goal_state, wind_strength,
                 alpha=0.1, gamma=0.9, epsilon=0.1, num_episodes=1000):
        self.num_rows = num_rows
        self.num_cols = num_cols
        self.start_state = start_state
        self.goal_state = goal_state
        self.wind_strength = wind_strength
        self.alpha = alpha
        self.gamma = gamma
        self.epsilon = epsilon
        self.num_episodes = num_episodes

        # Initialize Q-table
        self.Q = np.zeros((num_rows, num_cols, 4))  # 4 actions: up, down, left, right

    def select_action(self, state):
        if np.random.rand() < self.epsilon:
            return np.random.randint(4)  # random action
        else:
            return np.argmax(self.Q[state[0], state[1]])

    def apply_action(self, state, action):
        next_state = (state[0] - self.wind_strength[state[1]], state[1])

        # Clip to ensure the indices are within the valid range
        next_state = (np.clip(next_state[0], 0, self.num_rows - 1), np.clip(next_state[1], 0, self.num_cols - 1))

        if action == 1:
            next_state = (state[0] + self.wind_strength[state[1]], state[1])
            next_state = (np.clip(next_state[0], 0, self.num_rows - 1), np.clip(next_state[1], 0, self.num_cols - 1))
        elif action == 2:
            next_state = (state[0], max(0, state[1] - 1))
        elif action == 3:
            next_state = (state[0], min(self.num_cols - 1, state[1] + 1))

        return next_state

    def q_learning(self):
        for episode in range(self.num_episodes):
            state = self.start_state
            total_reward = 0

            while state != self.goal_state:
                action = self.select_action(state)
                next_state = self.apply_action(state, action)

                reward = -1

                best_next_action = np.argmax(self.Q[next_state[0], next_state[1]])
                self.Q[state[0], state[1], action] += self.alpha * (
                    reward + self.gamma * self.Q[next_state[0], next_state[1], best_next_action] -
                    self.Q[state[0], state[1], action])

                state = next_state
                total_reward += reward

            if episode % 100 == 0:
                print(f"Episode {episode}, Total Reward: {total_reward}")

    def visualize_policy(self):
        policy = np.argmax(self.Q, axis=2)

        # Display the policy
        print("\nPolicy:")
        for row in policy:
            print(row)

        # Visualize the policy
        plt.imshow(policy, cmap='cool', interpolation='none', origin='upper', extent=[0, self.num_cols, 0, self.num_rows])
        plt.title('Learned Policy')
        plt.colorbar(label='Action (0: up, 1: down, 2: left, 3: right)')
        plt.show()
