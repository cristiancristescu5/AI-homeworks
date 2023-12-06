import numpy as np
from enum import Enum


class WindyGridQLearning:

    class Move(Enum):
        UP = 0
        DOWN = 1
        RIGHT = 2
        LEFT = 3

    def __init__(self, starting_cell, end_cell, winds):
        self.starting_cell = starting_cell
        self.state = starting_cell
        self.end_cell = end_cell
        self.winds = winds

    def run(self):
        q_table = np.zeros()
