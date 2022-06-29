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

# Class Im-balance:

* It is noted that the data distribution in the target class is not balanced. This may induce a bias in the model. Hence, the imbalance is handeled using SMOTE(Synthetic Minority Oversampling Technique). Over sampling is performed for each training set during 10-fold cross validation.

# Cross Validation:

* 


# Reference:
[1]. Prasetiyowati, M.I., Maulidevi, N.U. & Surendro, K. Determining threshold value on information gain feature selection to increase speed and prediction accuracy of random forest. J Big Data 8, 84 (2021). https://doi.org/10.1186/s40537-021-00472-4