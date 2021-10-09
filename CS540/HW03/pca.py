from scipy.linalg import eigh
import numpy as np
import matplotlib.pyplot as plt

# load the dataset from a provided .npy file, re-center it 
# around the origin and return it as a NumPy array of floats
def load_and_center_dataset(filename):
    # TODO: add your code here
    x = np.load(filename)
    # Xicenter = Xi - mean of (X) 
    x = x - np.mean(x, axis = 0)
    return x

# calculate and return the covariance matrix of the dataset as 
# a NumPy matrix (d x d array)
def get_covariance(dataset):
    # TODO: add your code here
    # Sigma covariance = Sigma xi . xi(transsposed)
    sd_covariance = np.dot(np.transpose(dataset), dataset)
    # S = (1 / n - 1) * sigma xiTi
    sd_covariance = 1 / (len(dataset) - 1) * sd_covariance
    return sd_covariance 

# perform eigen decomposition on the covariance matrix S and return 
# a diagonal matrix (NumPy array) with the largest m eigenvalues on 
# the diagonal in descending order, and a matrix (NumPy array) with 
# the corresponding eigenvectors as columns
def get_eig(S, m):
    # TODO: add your code here
    val, vect = eigh(S, subset_by_index=[len(S)-m, len(S)-1])
    eigVal = val.argsort()[::-1]
    eigVec = vect[:, eigVal]
    retMat = np.diag(val[eigVal])

    return retMat, eigVec


# similar to get_eig, but instead of returning the first m, 
# return all eigenvalues and corresponding eigenvectors in 
# similar format as get_eig that explain more than perc % of 
# variance (specifically, please make sure eigenvalues are 
# returned in descending orders)
def get_eig_perc(S, perc):
    # TODO: add your code here
    # +1 on dimension because minusing down
    dimension = 1025
    sum = 0 
    
    for i in eigh(S, eigvals_only = True):
        sum += i

    for j in eigh(S, eigvals_only = True):
        dimension -= 1

        if (j / sum) > perc:
            break

    return get_eig(S, dimension)
    

# project each (d x 1) image into your m-dimensional subspace 
# (spanned by m vectors of size d x 1) and return the new 
# representation as a d x 1 NumPy array
def project_image(img, U):
    # TODO: add your code here
    index = 0
    dimension = 1024
    # alpha ij = transpose(U_j) * x_i, 
    # Transpose(U_j) = U_T; x_i = img
    alpha_ij = np.dot(np.transpose(U), img)

    # x_i_Projection = sigma(alpha_ij * U_j); from j = 1 to m
    # initialize
    x_i_Projection = np.ndarray(shape = (dimension,))
    
    # traverse U to do summation
    for U_j in U:
        # x_i_Projection (at j = index) = alpha_ij * U_j 
        x_i_Projection[index] = np.dot(alpha_ij, U_j)
        index += 1
    
    return x_i_Projection


# use matplotlib to display a visual representation of the 
# original image and the projected image side-by-side
def display_image(orig, proj):
    # TODO: add your code here

    # figsize define the magnitude of diagram
    figure, axs = plt.subplots(1, 2, figsize = (18, 6))
    # First Subplot
    axs[0].set_title("Original")
    original = np.reshape(orig, (32, 32), order = "F")

    # Second subplot Title
    axs[1].set_title("Projection")  
    reshaped = np.reshape(proj, (32, 32), order = "F")
    
    # render the original image in the first & second subplot
    first_subplot = axs[0].imshow(original, aspect = "equal")
    second_subplot = axs[1].imshow(reshaped, aspect = "equal")

    # use return value of imshow(). create a colorbar for each image
    figure.colorbar(first_subplot, ax = axs[0])
    figure.colorbar(second_subplot, ax = axs[1])

    # display the diagram
    plt.show()
