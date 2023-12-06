import numpy as np
from enum import Enum


class QLearning:
    learning_rate = 0.1
    epsilon = 0.1
    default_reward = -1
    # focuses on long-term reward
    discount_factor = 0.7

    class Move(Enum):
        UP = 0
        RIGHT = 1
        DOWN = 2

    def __init__(self, starting_cell, end_cell, grid_shape, winds):
        self.starting_cell = starting_cell
        self.end_cell = end_cell
        self.grid_shape = grid_shape
        self.wind_force = winds
        self.q_table = None

    def get_action(self, state) -> int:
        # checks if the action taken will be according to the policy, or it will explore
        return np.random.randint(3) \
            if np.random.random() < QLearning.epsilon \
            else np.argmax(self.q_table[state[0], state[1]])

    def transition(self, state, action) -> (int, int):
        assert action in range(3)
        # applying the wind force
        next_state = (state[0] - self.wind_force[state[1]], state[1])

        # assuring that the next state does not get out of the grid
        next_state = (
            np.clip(state[0], 0, self.grid_shape[0] - 1),
            np.clip(state[1], 0, self.grid_shape[1] - 1)
        )

        if action == QLearning.Move.UP:
            next_state = (state[0] + self.wind_force[state[1]], state[1])
            next_state = (
                np.clip(state[0], 0, self.grid_shape[0] - 1),
                np.clip(state[1], 0, self.grid_shape[1] - 1)
            )
        elif action == QLearning.Move.RIGHT:
            next_state = (state[0], max(0, state[1] - 1))
        else:
            next_state = (state[0], min(self.grid_shape[1] - 1, state[1] + 1))

        return next_state

    def run(self, episodes=10):
        self.q_table = np.zeros(self.grid_shape + (4, ))
        for current_episode in range(1, episodes + 1):
            state = self.starting_cell
            # total_reward = 0

            while state != self.end_cell:
                action = self.get_action(state)
                next_state = self.transition(state, action)

                # reward = QLearning.default_reward
                best_next_move = np.argmax(self.q_table[next_state[0], next_state[1]])
                self.q_table[state[0], state[1], action] = ((1 - QLearning.learning_rate)
                                                            * self.q_table[state[0], state[1], action]
                                                            + QLearning.discount_factor
                                                            * self.q_table[
                                                                next_state[0], next_state[1], best_next_move])

                state = next_state
