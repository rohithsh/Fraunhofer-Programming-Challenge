# Fraunhofer-Programming-Challenge
This repository contains the solution to the hiring programming challenge given by Fraunhofer IEM.

# Dataset Description:

The provided dataset contains java methods and features extracted for each method. The methods are classified as vulnerable or not. Each datapoint has 40 features (including the classification class).

# Pre-processing:

* The data has 397 instances and 39 features(excluding the target label and method name).
* All features are numeric. No nominal or string values were found.
* 23% of the instances were missing values in attribute 'Property5'. The missing values were replaced using the *ReplaceMissingValues* class. The class uses the mean to replace null values for numeric attributes.

# Feature Selection:

* Four attributes were found to have constant values for all instances. Therefore they were removed from the data.
* The correlation between the attributes was determined using *PearsonsCorrelation*. A threshold of 0.75 & -0.75 was established to find highly correlated attributes. Five attributes causing high correlation were removed.
* **Dimensionality Reduction**: After removing nine attributes from the original dataset, the remaining attributes were ranked based on their Information Gain. A threshold is established based on the Standard Deviation of the information gains of all attributes *[1]*. Using the formula provided by the mentioned publication, the threshold was estimated as **0.028**. The data is reduced to 11 feature dimensions.

# Class Imbalance:

* It is noted that the data distribution in the target class is not balanced. This imbalance may induce a bias in the model. Hence, the imbalance is handled using *SMOTE*(Synthetic Minority Oversampling Technique). Oversampling is performed for each training set during a 10-fold cross-validation.


# Train-Test Split & Cross-Validation:

* A train-test ratio of 70:30 is used for evaluating the model.
In addition, * 10-fold cross-validation is performed on the model. The minority class in training data is over-sampled during each iteration using SMOTE.

# Modelling &  Hyper Parameter Tuning

* The provided task is a classification problem. Hence, an Instance-based KNN classifier is used for classifying the test data.
* The hyper-parameter of KNN, i.e. k, is tuned for the maximum f1-score. The results of the evaluation are as follow: <br>
k <br>
*2, F1-Score: 0.9234368052346724* <br>
3, F1-Score: 0.8901330486528533 <br>
4, F1-Score: 0.8935188534584096 <br>
5, F1-Score: 0.8689524762405518 <br>
6, F1-Score: 0.8725543421487512 <br>
7, F1-Score: 0.8567850617643261 <br>
8, F1-Score: 0.867469978488822 <br>
9, F1-Score: 0.8562505223968071 <br>
10, F1-Score: 0.862038765286379 <br>

k = 2 is selected as the hyper-parameter of choice.
# Results: 
*10-Fold Cross Validation* <br>
Iteration: f1-score <br>
0:0.9316455696202531 <br>
1:0.9191270860077022 <br>
2:0.9174468085106383 <br>
3:0.920410783055199 <br>
4:0.9236252545824848 <br>
5:0.9237933954276036 <br>
6:0.9235230155853571 <br>
7:0.923954372623574 <br>
8:0.9251968503937007 <br>
9:0.9256449165402124 <br>

**Average f1-score**: *0.9234368052346724*

# Reference:
[1]. Prasetiyowati, M.I., Maulidevi, N.U. & Surendro, K. Determining threshold value on information gain feature selection to increase speed and prediction accuracy of random forest. J Big Data 8, 84 (2021). https://doi.org/10.1186/s40537-021-00472-4
