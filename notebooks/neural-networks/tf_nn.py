from tensorflow.keras.layers import Dense
from tensorflow.keras.layers import Input
from tensorflow.keras.models import Model
from tensorflow.keras.utils import plot_model
import tensorflow as tf
import numpy as np
import pandas as pd
import urllib

def run():
    print(f'Using Tensorflow version {tf.__version__}')

    # Data Constants

    SEED = 10
    DATA_FILE = "spiral.csv"
    TRAIN_SIZE = 0.7
    VAL_SIZE = 0.15
    TEST_SIZE = 0.15
    SHUFFLE = True
    


    # TensorFlow Constants
    
    INPUT_DIM = X_train.shape[1]
    HIDDEN_DIM = 100
    NUM_CLASSES = len(classes)

    np.random.seed(SEED)
    tf.random.set_seed(SEED)


    print(f"Downloading {DATA_FILE}")
    url = "https://raw.githubusercontent.com/madewithml/basics/master/data/spiral.csv"
    response = urllib.request.urlopen(url)
    html = response.read()
    with open(DATA_FILE, 'wb') as fp:
        fp.write(html)

    df = pd.read_csv(DATA_FILE, header=0)
    X = df[['X1', 'X2']].values
    y = df['color'].values
    print(df.head(5))
    return 0


if __name__ == '__main__':
    run()
