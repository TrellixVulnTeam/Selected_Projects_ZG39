import copy
import numpy as np
import heapq

'''
    Original Author: APCRP DEE
    Source: 
        https://www.youtube.com/watch?v=XRqA6RQr3SQ
    Link listed give me idea of implementation
    Discussed with: Guanlin Chen
'''
#---------------------------helper function of print_succ(state) Starts--------------------------------
# This method calculates the manhattan distance for each incorrect
# placed tiles
def manhattan_dist(curr, goal):
    
    dist = abs(curr[0] - goal[0]) + abs(curr[1] - goal[1])
    return dist


# This method calculates the total h value of each states and return it
def h_calculation(state):
    map = [(0, 0), (0, 1), (0, 2), (1, 0), (1, 1),
        (1, 2), (2, 0), (2, 1), (2, 2)]
    goal_state = [1, 2, 3, 4, 5, 6, 7, 8, 0]
    h = 0
    
    if state == goal_state:
        return 0
    else:
        for i in range (9):
            # ignore when equals 0, as it is NULL, not real tile:
            # calculate h for incorrect placed tiles
            if state[i] != 0 and state[i] != goal_state[i]:
                    curr = map[i]
                    goal = map[state[i] - 1]
                    h += manhattan_dist(curr, goal) 
                        
        return h    


# This method generate all possible successor states as long as the state
# is not match to identical case; helper function of print_succ
def get_successors(state):
    next_state = copy.deepcopy(state)
    return_state = []    

    # Corner Case
    if state[0] == 0:
        next_state = copy.deepcopy(state)
        next_state[0] = next_state[1]
        next_state[1] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[0] = next_state[3]
        next_state[3] = 0
        return_state.append(next_state)
    
    elif state[2] == 0:
        next_state = copy.deepcopy(state)
        next_state[2] = next_state[1]
        next_state[1] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[2] = next_state[5]
        next_state[5] = 0
        return_state.append(next_state)
        
    elif state[6] == 0:
        next_state = copy.deepcopy(state)
        next_state[6] = next_state[3]
        next_state[3] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[6] = next_state[7]
        next_state[7] = 0
        return_state.append(next_state)
    
    elif state[8] == 0:
        next_state = copy.deepcopy(state)
        next_state[8] = next_state[5]
        next_state[5] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[8] = next_state[7]
        next_state[7] = 0
        return_state.append(next_state)

    # Side
    elif state[1] == 0:
        next_state = copy.deepcopy(state)
        next_state[1] = next_state[0]
        next_state[0] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[1] = next_state[2]
        next_state[2] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[1] = next_state[4]
        next_state[4] = 0
        return_state.append(next_state)

    elif state[3] == 0:
        next_state = copy.deepcopy(state)
        next_state[3] = next_state[0]
        next_state[0] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[3] = next_state[4]
        next_state[4] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[3] = next_state[6]
        next_state[6] = 0
        return_state.append(next_state)

    elif state[5] == 0:
        next_state = copy.deepcopy(state)
        next_state[5] = next_state[2]
        next_state[2] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[5] = next_state[4]
        next_state[4] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[5] = next_state[8]
        next_state[8] = 0
        return_state.append(next_state)

    elif state[7] == 0:
        next_state = copy.deepcopy(state)
        next_state[7] = next_state[4]
        next_state[4] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[7] = next_state[6]
        next_state[6] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[7] = next_state[8]
        next_state[8] = 0
        return_state.append(next_state)
    
    # Center
    elif state[4] == 0:
        next_state = copy.deepcopy(state)
        next_state[4] = next_state[1]
        next_state[1] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[4] = next_state[3]
        next_state[3] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[4] = next_state[5]
        next_state[5] = 0
        return_state.append(next_state)

        next_state = copy.deepcopy(state)
        next_state[4] = next_state[7]
        next_state[7] = 0
        return_state.append(next_state)

    return sorted(return_state)
#---------------------------helper function of print_Succ(State) ENDS--------------------------------


# given a state of the map, represented as a single list
# of integers with a 0 in the empty space, print to the console
# all of the possible successor states
def print_succ(state):
    succ = get_successors(state)

    for succ_state in succ:
        print(str(succ_state) + " h=" + str(h_calculation(succ_state)))

#--------------------------solve helper function-------------------------------
def get_path(parent, curr):
    path = [curr]

    while str(curr) in parent.keys():
        curr = parent[str(curr)]
        path.append(curr)
    
    return path


def a_star_search(state):
    parent_index = -1
    g = 0
    h = h_calculation(state)
    pq = []
    passed = []
    parent = dict()

    goal_state = [1, 2, 3, 4, 5, 6, 7, 8, 0]
    
    heapq.heappush(pq, (g+h, state, (g, h, -1)))

    while len(pq) > 0:
        h, curr, info = heapq.heappop(pq)

        passed.append(curr)

        if curr == goal_state:
            return get_path(parent, curr)

        for x in get_successors(curr):
            g = info[0] + 1
            h = h_calculation(x)

            if x not in passed:
                parent[str(x)] = curr

                heapq.heappush(
                    pq, (g+h, x, (g, h_calculation(curr), parent_index+1)))
    
    return 0
#--------------------------solve helper function-------------------------------

# given a state of the puzzle, perform the A* search algorithm and print
# the path from the current state to the goal state
def solve(state):
    passed_path = a_star_search(state)
    moves = 0
    
    length = len(passed_path) - 1
    
    for i in range(length, -1, -1):
        print(passed_path[i], " h=", h_calculation(passed_path[i]), " moves: ", moves, sep = "")
        moves += 1
