import numpy as np
from windy_grid import QLearning


def main():
    algorithm_instance = QLearning(
        (3, 0),
        (3, 7),
        (7, 10),
        np.array([0, 0, 0, 1, 1, 1, 2, 2, 1, 0])
    )
    algorithm_instance.run()
    print(algorithm_instance.q_table)


if __name__ == "__main__":
    main()
