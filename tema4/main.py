from dataloader import load_data
from layer import Layer
import func
from neural_network import NeuralNetwork

learning_rate = 0.01
train_data, test_data = load_data()

neural__network = NeuralNetwork([
    Layer(7, 10, func.softmax),
    Layer(10, 7, func.softmax),
    Layer(7, 3, func.softmax)
])

print(neural__network.feed_forward(train_data[0][:-1]))

print(func.softmax_dx(func.softmax([1, 2, 3, 4])))
