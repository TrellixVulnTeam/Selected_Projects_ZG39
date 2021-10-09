import os
import math

#These first two functions require os operations and so are completed for you
#Completed for you
def load_training_data(vocab, directory):
    """ Create the list of dictionaries """
    top_level = os.listdir(directory)
    dataset = []
    for d in top_level:
        if d[-1] == '/':
            label = d[:-1]
            subdir = d
        else:
            label = d
            subdir = d+"/"
        files = os.listdir(directory+subdir)
        for f in files:
            bow = create_bow(vocab, directory+subdir+f)
            dataset.append({'label': label, 'bow': bow})
    return dataset

#Completed for you
def create_vocabulary(directory, cutoff):
    """ Create a vocabulary from the training directory
        return a sorted vocabulary list
    """

    top_level = os.listdir(directory)
    vocab = {}
    for d in top_level:
        subdir = d if d[-1] == '/' else d+'/'
        files = os.listdir(directory+subdir)
        for f in files:
            with open(directory+subdir+f,'r', encoding="utf-8") as doc:
                for word in doc:
                    word = word.strip()
                    if not word in vocab and len(word) > 0:
                        vocab[word] = 1
                    elif len(word) > 0:
                        vocab[word] += 1
    return sorted([word for word in vocab if vocab[word] >= cutoff])

#The rest of the functions need modifications ------------------------------
#Needs modifications
def create_bow(vocab, filepath):
    """ Create a single dictionary for the data
        Note: label may be None
    """
    # This is the empty bow creayed
    bow = {}
    # TODO: add your code here
    # Open the file in doc typpe
    with open(filepath, 'r', encoding="utf-8") as doc:
        for word_Reading in doc:
            word_Reading = word_Reading.strip()
            for word in vocab:
                if word_Reading == word: 
                    if word_Reading in bow:
                        bow[word] += 1
                    elif not word_Reading in bow:
                        bow[word] = 1
            if word_Reading not in bow:
                if None in bow: 
                    bow[None] += 1
                elif not None in bow:
                    bow[None] = 1
    return bow
#------------------------------------------------SECTION #1 TERMINATES------------------------------------------------

#------------------------------------------------SECTION #2 STARTS------------------------------------------------

#Needs modifications
def prior(training_data, label_list):
    """ return the prior probability of the label in the training set
        => frequency of DOCUMENTS
    """

    smooth = 1 # smoothing factor
    logprob = {}
    # TODO: add your code here

    for label in label_list:
        num_files_with_label = 0 # NFilesWithLabel
        for docu in training_data:
            if docu["label"] == label:
                num_files_with_label += 1
        
        # P(label = y) = (Num_files_with_label + smooth) / (Total_Num_files + 2)   
        logprob[label] = math.log((num_files_with_label + smooth) / \
            (len(training_data) + 2))
        
    return logprob

#Needs modifications
def p_word_given_label(vocab, training_data, label):
    """ return dictionary consisting the class conditional 
        probability of label over all words, with smoothing """

    smooth = 1 # smoothing factor
    word_prob = {}
    # TODO: add your code here

    ''' P(word | label):  how likely see this word given the doc
        is one with this label
        P(w | label) = (c(word) + 1) / (wc + (|v| + 1))
        c(word) = total word count over all documents of given label
        wc = total word count
        |v| = size of vocab
    '''
    
    total_word_count = 0 # wc
    word_prob[None] = 0 # c(None)
    for word in vocab:
        word_prob[word] = 0 # c(Word)


    for docu in training_data:
        if docu["label"] == label:
            for word in docu["bow"]:
                # Get total word count, wc
                total_word_count += docu["bow"][word]

                # Get c(word)
                if word in vocab:
                    word_prob[word] += docu["bow"][word]
                
                # Get c(None)
                elif not word in vocab:
                    word_prob[None] += docu["bow"][word]

    for word in word_prob:    
        word_prob[word] = math.log(
            (word_prob[word] + smooth * 1) / \
                (total_word_count + smooth * (len(vocab) + 1)))

    return word_prob
#------------------------------------------------SECTION #2 TERMINATES------------------------------------------------

#------------------------------------------------SECTION #3 STARTS------------------------------------------------

##################################################################################
#Needs modifications
def train(training_directory, cutoff):
    """ return a dictionary formatted as follows:
            {
             'vocabulary': <the training set vocabulary>,
             'log prior': <the output of prior()>,
             'log p(w|y=2016)': <the output of p_word_given_label() for 2016>,
             'log p(w|y=2020)': <the output of p_word_given_label() for 2020>
            }
    """
    retval = {}
    label_list = os.listdir(training_directory)
    # TODO: add your code here
    vocab_model = create_vocabulary(training_directory, cutoff)
    log_prior = prior(load_training_data(
        vocab_model, training_directory), label_list)
    p_2016 = p_word_given_label(vocab_model, load_training_data(
        vocab_model, training_directory), "2016")
    p_2020 = p_word_given_label(vocab_model, load_training_data(
        vocab_model, training_directory), "2020")

    
    retval["vocabulary"] = vocab_model
    retval["log prior"] = log_prior
    retval["log p(w|y=2016)"] = p_2016
    retval["log p(w|y=2020)"] = p_2020

    return retval

#Needs modifications
def classify(model, filepath):
    """ return a dictionary formatted as follows:
            {
             'predicted y': <'2016' or '2020'>,
             'log p(y=2016|x)': <log probability of 2016 label for the document>,
             'log p(y=2020|x)': <log probability of 2020 label for the document>
            }
    """
    retval = {}
    # TODO: add your code here
    
    vocab_model = model["vocabulary"]
    prob_2016 = model["log prior"]["2016"]
    prob_2020 = model["log prior"]["2020"]
    predict_y = 0
    
    test = create_bow(vocab_model, filepath)

    # log p(y = 2016|x) & log p(y = 2020|x)
    for word in test:
        prob_2016 += model["log p(w|y=2016)"][word] * \
            test[word]
        prob_2020 += model["log p(w|y=2020)"][word] * \
            test[word]

    if prob_2016 > prob_2020:
        predict_y = "2016" 
    else:
        predict_y = "2020"

    retval["log p(y=2016|x)"] = prob_2016
    retval["log p(y=2020|x)"] = prob_2020
    retval["predicted y"] = predict_y

    return retval
