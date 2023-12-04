import numpy as np
from transition import Move
from qlearning import QLearning
from nicetry import WindyGridworldQlearning


def main():
    env = WindyGridworldQlearning(num_rows=7, num_cols=10, start_state=(3, 0), goal_state=(3, 7),
                                  wind_strength=[0, 0, 0, 1, 1, 1, 2, 2, 1, 0])
    env.q_learning()
    env.visualize_policy()
    # Environment parameters
    # num_rows = 7
    # num_cols = 10
    # start_state = (3, 0)
    # goal_state = (3, 7)
    # wind_strength = [0, 0, 0, 1, 1, 1, 2, 2, 1, 0]
    #
    # # Q-learning parameters
    # alpha = 0.1  # learning rate
    # gamma = 0.9  # discount factor
    # epsilon = 0.1  # exploration-exploitation trade-off
    # num_episodes = 1000
    #
    # # Initialize Q-table
    # Q = np.zeros((num_rows, num_cols, 4))  # 4 actions: up, down, left, right
    #
    # # Q-learning algorithm
    # for episode in range(num_episodes):
    #     state = start_state
    #     total_reward = 0
    #
    #     while state != goal_state:
    #         # Exploration-exploitation trade-off
    #         if np.random.rand() < epsilon:
    #             action = np.random.randint(4)  # random action
    #         else:
    #             action = np.argmax(Q[state[0], state[1]])
    #
    #         # Apply action, considering wind
    #         next_state = (state[0] - wind_strength[state[1]], state[1])
    #         if action == 1:
    #             next_state = (state[0] + wind_strength[state[1]], state[1])
    #         elif action == 2:
    #             next_state = (state[0], max(0, state[1] - 1))
    #         elif action == 3:
    #             next_state = (state[0], min(num_cols - 1, state[1] + 1))
    #
    #         # Reward for all transitions is -1
    #         reward = -1
    #
    #         # Q-value update
    #         best_next_action = np.argmax(Q[next_state[0], next_state[1]])
    #         Q[state[0], state[1], action] += alpha * (
    #                 reward + gamma * Q[next_state[0], next_state[1], best_next_action] -
    #                 Q[state[0], state[1], action])
    #
    #         state = next_state
    #         total_reward += reward
    #
    #     if episode % 100 == 0:
    #         print(f"Episode {episode}, Total Reward: {total_reward}")
    #
    # # Extracting the policy
    # policy = np.argmax(Q, axis=2)
    #
    # # Display the policy
    # print("\nPolicy:")
    # for row in policy:
    #     print(row)
    # Initialize the parameters
    # alpha = 0.5
    # gamma = 0.95
    # epsilon = 0.1
    # num_episodes = 5000
    #
    # # Initialize the state space and action space
    # num_states = (7, 10)
    # num_actions = 4  # up, down, left, right
    # wind = np.array([0, 0, 0, 1, 1, 1, 2, 2, 1, 0])
    #
    # # Initialize the Q table
    # Q = np.zeros(num_states + (num_actions,))
    #
    # # Define the function to get the next state
    # def get_next_state(state, action):
    #     i, j = state
    #     if action == 0:  # up
    #         return max(i - 1 - wind[j], 0), j
    #     elif action == 1:  # down
    #         return max(min(i + 1 - wind[j], num_states[0] - 1), 0), j
    #     elif action == 2:  # left
    #         return max(i - wind[j], 0), max(j - 1, 0)
    #     elif action == 3:  # right
    #         return max(i - wind[j], 0), min(j + 1, num_states[1] - 1)
    #
    # # Q-learning algorithm
    # for episode in range(0, num_episodes):
    #     state = (3, 0)
    #     while state != (3, 7):
    #         # Select the action with the highest Q value in state s'
    #         if np.random.uniform(0, 1) < epsilon:
    #             action = np.random.choice(num_actions)
    #         else:
    #             action = np.argmax(Q[state])
    #         next_state = get_next_state(state, action)
    #         reward = -1
    #         # Update the Q values
    #         Q[state][action] = Q[state][action] + alpha * (reward + gamma * np.max(Q[next_state]) - Q[state][action])
    #         # Update the current state
    #         state = next_state
    #
    # # Show the policy determined by the algorithm
    # policy = {state: np.argmax(Q[state]) for state in np.ndindex(num_states)}
    # print(policy)
    # num_states = (7, 10)
    # num_actions = 4
    # Q = np.zeros(num_states + (num_actions,), dtype=np.float32)
    #
    # print(Q)


if __name__ == "__main__":
    main()
