import numpy as np

from dataloader import load_data
from layer import Layer
import func
from neural_network import NeuralNetwork

learning_rate = 0.01
train_data, test_data = load_data()
print(f"Train data shape: {train_data.shape}")
print(f"Test data shape: {test_data.shape}")
epochs = 15

neural__network = NeuralNetwork([
    Layer(7, 6, func.softmax),
    Layer(6, 5, func.softmax),
    Layer(5, 4, func.softmax),
    Layer(4, 3, func.softmax)
])

neural__network.train_model(train_data)
