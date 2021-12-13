import gym
import random
import numpy as np
import time
from collections import deque
import pickle

from collections import defaultdict


EPISODES =   20000
LEARNING_RATE = .1 # alpha
DISCOUNT_FACTOR = .99 # gamma
EPSILON = 1
EPSILON_DECAY = .999



def default_Q_value():
    return 0


if __name__ == "__main__":



    random.seed(1)
    np.random.seed(1)
    env = gym.envs.make("FrozenLake-v0")
    env.seed(1)
    env.action_space.np_random.seed(1)


    Q_table = defaultdict(default_Q_value) # starts with a pessimistic estimate of zero reward for each state.

    episode_reward_record = deque(maxlen=100)


    for i in range(EPISODES):
        episode_reward = 0

        #TODO PERFORM Q LEARNING
        next_state = env.reset()
        done = False
        
        # act according to epsilon-greedy policy
        while not done:
            curr_state = next_state
            
            if random.uniform(0, 1) < EPSILON:
                action = env.action_space.sample()
            else:
                action = np.argmax(
                    np.array([Q_table[(curr_state, i)] for i in range(env.action_space.n)]))

            next_state, episode_reward, done, info = env.step(action)
             
            if done:
                q_final = episode_reward - Q_table[curr_state, action]
                Q_table[curr_state, action] += LEARNING_RATE * q_final
            else:
                q_max = episode_reward + np.max(np.array([Q_table[(next_state, i)] for i in range(env.action_space.n)])) \
                    - Q_table[curr_state, action]
                Q_table[curr_state, action] +=  LEARNING_RATE * q_max
        
        EPSILON *= EPSILON_DECAY
        episode_reward_record.append(episode_reward)
        
        if i%100 == 0 and i>0:
            print("LAST 100 EPISODE AVERAGE REWARD: " + str(sum(list(episode_reward_record))/100))
            print("EPSILON: " + str(EPSILON) )

        
    
    ####DO NOT MODIFY######
    model_file = open('Q_TABLE.pkl' ,'wb')
    pickle.dump([Q_table,EPSILON],model_file)
    #######################






