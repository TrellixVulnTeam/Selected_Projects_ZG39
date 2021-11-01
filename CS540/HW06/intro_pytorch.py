import numpy as np
import torch
import torch.nn as nn
import torch.nn.functional as F
from torch.nn.modules.linear import Linear
import torch.optim as optim
from torchvision import datasets, transforms

# Feel free to import other packages, if needed.
# As long as they are supported by CSL machines.


def get_data_loader(training = True):
    """
    TODO: implement this function.

    INPUT: 
        An optional boolean argument (default value is True for training dataset)

    RETURNS:
        Dataloader for the training set (if training = True) or the test set (if training = False)
    """
    custom_transform = transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize((0.1307,), (0.3081,))
    ])

    train_set = datasets.MNIST('./data', train=True, download=True,
                               transform=custom_transform)

    test_set = datasets.MNIST('./data', train=False,
                              transform=custom_transform)

    if training == True:
        # train loader
        return torch.utils.data.DataLoader(train_set, batch_size=50)
    else:
        # test loader
        return torch.utils.data.DataLoader(test_set, batch_size=50, shuffle=False)


def build_model():
    """
    TODO: implement this function.

    INPUT: 
        None

    RETURNS:
        An untrained neural network model
    """
    model = nn.Sequential(
        # flatten layer
        nn.Flatten(),
        # dense layer
        nn.Linear(784, 128),
        nn.ReLU(),
        nn.Linear(128, 64),
        nn.ReLU(),
        nn.Linear(64, 10)
    )

    return model


def train_model(model, train_loader, criterion, T):
    """
    TODO: implement this function.

    INPUT: 
        model - the model produced by the previous function
        train_loader  - the train DataLoader produced by the first function
        criterion   - cross-entropy 
        T - number of epochs for training

    RETURNS:
        None
    """
    opt = optim.SGD(model.parameters(), lr=0.001, momentum=0.9)

    # set model to train model before iterating
    model.train()

    for epoch in range(T):  # outer for loop iterates over epochs
        # initialize loss & accuracy
        average_loss = 0.0
        accuracy = 0

        # inner for loop iterates over batches of (iamges, labels) pairs from
        # the train DataLoader
        for i, datas in enumerate(train_loader, 0):
            # get the inputs; data is a list of [images, labels]
            images, labels = datas

            # zero param gradients
            opt.zero_grad()

            # forward + backward + optimize
            # calculate outputs by running images through nn
            outputs = model(images)
            # class with highest energy is choosed for prediction
            _, predict = torch.max(outputs.data, 1)
            loss = criterion(outputs, labels)
            loss.backward()
            opt.step()

            # write statistics into print variables
            average_loss += loss.item()
            accuracy += (predict == labels).sum().item()

        # print out statistics
        print("Train Epoch: %d Accuracy: %d/60000(%.2f%%) Loss: %.3f" %
              (epoch, accuracy, accuracy * 100 / 60000, average_loss * 50 / 60000))

            

def evaluate_model(model, test_loader, criterion, show_loss = True):
    """
    TODO: implement this function.

    INPUT: 
        model - the the trained model produced by the previous function
        test_loader    - the test DataLoader
        criterion   - cropy-entropy 

    RETURNS:
        None
    """
    total = 0
    accuracy = 0
    average_loss = 0.0

    model.eval()

    # disable gradient tracking during testing
    with torch.no_grad():
        for datas in test_loader:
            # get the inputs; data is a list of [inputs, labels]
            images, labels = datas

            # forward + backward + optimize
            # calculate outputs by running images through nn
            outputs = model(images)
            # class with highest energy is choosed for prediction
            _, predict = torch.max(outputs.data, 1)
            loss = criterion(outputs, labels)

            # write statistics into print variables
            total += labels.size(0)
            accuracy += (predict == labels).sum().item()
            average_loss += loss.item()

    # print test Loss & test Accuracy
    if show_loss == True:
        print("Average loss: %.4f\nAccuracy: %.2f%%" %
              (average_loss / total, accuracy * 100 / total))
    else:
        print("Accuracy: %.2f%%" % (accuracy * 100 / total))
    


def predict_label(model, test_images, index):
    """
    TODO: implement this function.

    INPUT: 
        model - the trained model
        test_images   -  test image set of shape Nx1x28x28
        index   -  specific index  i of the image to be tested: 0 <= i <= N - 1


    RETURNS:
        None
    """
    class_names = ['zero', 'one', 'two', 'three',
                   'four', 'five', 'six', 'seven', 'eight', 'nine']
    logits = model(test_images)
    prob = F.softmax(logits, dim=1)
    list_prob = []

    # add probability into list_prob
    for i in range(0, 10):
        list_prob.append((class_names[i], prob[index][i].item() * 100))

    # rank in descending order of probability
    list_prob.sort(key=lambda x: x[1], reverse=True)

    # print out the first 3 greatest probability
    for i in range(0, 3):
        print("%s: %.2f%%" % (list_prob[i][0], list_prob[i][1]))

if __name__ == '__main__':
    '''
    Feel free to write your own test code here to exaime the correctness of your functions. 
    Note that this part will not be graded.
    '''
    criterion = nn.CrossEntropyLoss()
