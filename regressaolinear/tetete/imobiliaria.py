import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn import preprocessing
from sklearn import metrics

data = pd.read_csv(r'porquenaocompraremibiruba.csv')

caracteristicasMatriz = data[["area", "numquart","bairro"]].to_numpy()
valoresVetor = data[["preco"]].to_numpy()

x_train, x_test, y_train, y_test = train_test_split(caracteristicasMatriz, valoresVetor, test_size=0.2, random_state=0)
lm = LinearRegression()
lm.fit(x_train, y_train)
predictions = lm.predict(x_test)


print ('MAE :', metrics.mean_absolute_error (y_test, predictions))
print ('MSE :', metrics.mean_squared_error (y_test, predictions))
print ('RMSE :', np.sqrt (metrics.mean_squared_error (y_test, predictions)))

areateste = int(input("\nInsira a area da casa  em centena de milhares: "))
quartoteste = int(input("\nInsira o numero de quartos: "))
bairroteste = int(input("\nInsira o bairro: \nLegenda: Santa Helena = 1, Colinas = 2, Unida = 3, Bangu = 4, Floresta = 5, Hermany = 6, Esperanca = 7, Jardim = 8\nCentro = 9, Progresso = 10, Vila Odila = 11, Sao Jacob = 12, Planalto = 13, Por do sol = 14, Santa Clara = 15, Buena Vita = 16\n "))
teste = np.array([areateste, quartoteste, bairroteste]).reshape(1,-1)

print("valor estimado totalmente preciso incapaz de falhar devido as excelentes metricas de ibiruba: ", lm.predict(teste))
