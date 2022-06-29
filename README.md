# Fraunhofer-Programming-Challenge
Solution to the hiring programming challenge given by Fraunhofer IEM.

# Dataset Description:

The provided dataset contains java methods and features extracted for each method. The methods are classified as vulnerable or not. Each datapoint has 40 features (including the classification class).

# Pre-processing:

* The data has 397 instances and 39 features(excluding the target label and method name).
* All features  are numeric. No nominal or string values were found.
* 23% of the instances were missing values in attribute 'Property5'. The missing values were replaced using *ReplaceMissingValues* class. The class uses the mean to replace null values for numeric attributes.

# Feature Selection:

* Four attributes were found to have constant values for all instances. Therefore they were removed from the data.
* The correlation between the attributes was determined using *PearsonsCorrelation*. A threshold of 0.75 & -0.75 was established to find highly correlated attributes. Five attributes causing high correlation were removed.
* **Dimensionality Reduction**: After removing 9 attributes from the original dataset, the remaining attributes were ranked based on their Information Gain. A threshold is established based on the Standard Deviation of the information gains of all attributes *[1]*. Using the formula provided by the mentioned publication, the threshold was estimated as **0.028**. The data is reduced to 11 feature dimensions.

# Class Imbalance:

* It is noted that the data distribution in the target class is not balanced. This may induce a bias in the model. Hence, the imbalance is handeled using *SMOTE*(Synthetic Minority Oversampling Technique). Over sampling is performed for each training set during 10-fold cross validation.

# Train-Test Split & Cross Validation:

* A train-test ratio of 70:30 is used for evaluating the model.
* A 10-fold cross validation is performed on the model. During each iteration, the minority class in training data is over sampled using SMOTE.

# Modelling &  Hyper Parameter Tuning

* The provided task is a classification problem. Hence, an Instance based KNN classifier is used for classifying the test data.
* The hyper-parameter of KNN i.e. k is tuned for the maximum f1-score. The results of the evaluation are as follow: <br>
k:f1-score <br>
2:0.867683169833834 <br>
3:0.8539488719682348 <br>
4:0.8333775339383818 <br>
5:0.8080655521979032 <br>
6:0.8379608856343255 <br>
7:0.8165438701284291 <br>
8:0.846577308681724 <br>
9:0.8519795860028123 <br>
10:0.8237384078152002 <br>

# Results: 
*10-Fold Cross Validation* <br>
Iteration: f1-score <br>
0:0.8852459016393444 <br>
1:0.8662790697674418 <br>
2:0.8549019607843138 <br>
3:0.8651026392961876 <br>
4:0.8716136631330977 <br>
5:0.8669950738916257 <br>
6:0.867003367003367 <br>
7:0.8653421633554084 <br>
8:0.8665351742274818 <br>
9:0.8678126852400712 <br>

**Average f1-score**: 0.867683169833834

# Reference:
[1]. Prasetiyowati, M.I., Maulidevi, N.U. & Surendro, K. Determining threshold value on information gain feature selection to increase speed and prediction accuracy of random forest. J Big Data 8, 84 (2021). https://doi.org/10.1186/s40537-021-00472-4
