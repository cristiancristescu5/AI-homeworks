import numpy as np
from weight_init import WeightInit
from dataloader import load_data
from layer import Layer
import func
from neural_network import NeuralNetwork

learning_rate = 0.01
train_data, test_data = load_data()
# print(f"Train data shape: {train_data.shape}")
# print(f"Test data shape: {test_data.shape}")
epochs = 15

neural__network = NeuralNetwork([
    Layer(7, 6, func.softmax, WeightInit.Xavier_Uniform_Distribution),
    Layer(6, 5, func.softmax, WeightInit.Xavier_Uniform_Distribution),
    Layer(5, 4, func.softmax, WeightInit.Xavier_Uniform_Distribution),
    Layer(4, 3, func.softmax, WeightInit.Xavier_Uniform_Distribution)
])

<<<<<<< main
neural__network.train_model(train_data)
=======
# print(len(train_data))
Backpropagation.run(neural__network, train_data, 400, 56, .01)

Validate.validate(neural__network, test_data)

>>>>>>> local
