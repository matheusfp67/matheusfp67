import tensorflow as tf
import numpy as np
from sklearn.model_selection import train_test_split 


nroBits = input("Insira o numero de bits a ser utilizado: ")
faixaInicio = input("Insira o inicio da faixa de valores: ")
faixaFim = input("Insira o final da faixa de valores: ")
faixaInicio = int(faixaInicio)
faixaFim = int(faixaFim)

dados = np.array([[int(s) for s in (np.binary_repr(0, int(nroBits)))]]) ##nro ia aqui
saida = np.array([[0, 0, 1]])

learningRate = 0.75
batchSize = 1
epochs = 200

for x in range(1, 2**int(nroBits)):
    
    teste = np.array([[int(s) for s in (np.binary_repr(x, int(nroBits)))]])
  
    dados = np.append(dados, teste, axis=0)
    
    if x < faixaInicio:    
        saida = np.append(saida, np.array([[0, 0, 1]],), axis=0)
    elif x > faixaInicio and x < faixaFim :  
        saida = np.append(saida, np.array([[0, 1, 0]],), axis=0)
    else:
        saida = np.append(saida, np.array([[1, 0, 0]],), axis=0)
    
X_train, X_test, y_train, y_test = train_test_split(dados, saida, test_size=0.33, random_state=42)

model =  tf.keras.models.Sequential()
model.add(tf.keras.Input(shape=(int(nroBits),)))
model.add(tf.keras.layers.Dense(int(nroBits), activation=tf.keras.activations.sigmoid))
model.add(tf.keras.layers.Dense(3, activation=tf.keras.activations.sigmoid))

model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=learningRate), # learning rate was 0.001 prior to this change
              loss=tf.keras.losses.MeanSquaredError(), 
              metrics=['mse', 'binary_accuracy'])

model.summary()

history = model.fit(X_train, y_train, batch_size=batchSize, epochs=epochs)

##predictions = model.predict_on_batch(teste)
##print(predictions)

print("Uso:")
result = model.predict(X_test)
print(result)


