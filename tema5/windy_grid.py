import numpy as np
from enum import Enum


class QLearning:
    learning_rate = 0.6
    epsilon = 0.1
    default_reward = -1
    # focuses on long-term reward
    discount_factor = 0.7

    class Move(Enum):
        UP = 0
        RIGHT = 1
        DOWN = 2
        LEFT = 3

    def __init__(self, starting_cell, end_cell, grid_shape, winds):
        self.starting_cell = starting_cell
        self.end_cell = end_cell
        self.grid_shape = grid_shape
        self.wind_force = winds
        self.q_table = None

    def get_action(self, state) -> int:
        # checks if the action taken will be according to the policy, or it will explore
        return np.random.randint(len(QLearning.Move)) \
            if np.random.random() < QLearning.epsilon \
            else np.argmax(self.q_table[state[0], state[1]])

    def transition(self, state, action) -> (int, int):
        """
        returns the new state, based on action, and wind-force on the current cell.
        if the new_state overflows the grid, based on wind or action, then it returns the old_state
        :param state:
        :param action:
        :return:
        """
        # applying the wind force and assuring that the next state does not get out of the grid
        next_state = max(state[0] - self.wind_force[state[1]], 0),  state[1]

        if action == QLearning.Move.UP.value:
            next_state = (max(next_state[0] - 1, 0), next_state[1])
        elif action == QLearning.Move.RIGHT.value:
            next_state = (next_state[0], min(self.grid_shape[1] - 1, next_state[1] + 1))
        elif action == QLearning.Move.DOWN.value:
            next_state = (min(self.grid_shape[0] - 1, next_state[0] + 1), next_state[1])
        else:
            next_state = (next_state[0], max(next_state[1] - 1, 0))

        return next_state

    def run(self, episodes=100000):
        self.q_table = np.zeros(self.grid_shape + (len(QLearning.Move),))
        for current_episode in range(1, episodes + 1):
            state = self.starting_cell
            while state != self.end_cell:
                action = self.get_action(state)
                next_state = self.transition(state, action)

                best_next_move = np.argmax(self.q_table[next_state[0], next_state[1]])

                self.q_table[state[0], state[1], action] = ((1 - QLearning.learning_rate)
                                                            * self.q_table[state[0], state[1], action]
                                                            + QLearning.learning_rate
                                                            * (QLearning.default_reward
                                                               + QLearning.discount_factor
                                                               * self.q_table[
                                                                   next_state[0], next_state[1], best_next_move]))

                state = next_state
