package com.solution;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.supervised.instance.SMOTE;

import org.apache.commons.math.stat.correlation.*;


public class App 
{

    //Checking the co-relation co-efficient b/w the features(attributes)
    public static void checkCorrelation( Instances data){
        int limit = data.numAttributes()-1;
        for(int i=1; i<limit; i++){
            for(int j=i+1; j<limit; j++){
                double corr = new PearsonsCorrelation().correlation(data.attributeToDoubleArray(i), data.attributeToDoubleArray(j));
                if(corr>=0.75){
                    System.out.println("Positive:"+i+":"+j+"="+corr);
                }
                else if(corr<=-0.75){
                    System.out.println("Negative:"+i+":"+j+"="+corr);
                }
            }
        }
    }

    public static Instances[] trainTestSplit(Instances data) throws Exception {
        RemovePercentage rp = new RemovePercentage();
        rp.setInputFormat(data);
        rp.setPercentage(30);
        Instances trainSet = Filter.useFilter(data, rp);
        rp = new RemovePercentage();
        rp.setInputFormat(data);
        rp.setPercentage(30);
        rp.setInvertSelection(true);
        Instances testSet = Filter.useFilter(data, rp);
        return new Instances[] {trainSet, testSet};
        
    }
    public static void main( String[] args ) throws Exception
    {
        DataSource source = new DataSource("data.arff");
        Instances data = source.getDataSet();

        // //Checking for String values
        for(int i = 1; i<data.numAttributes()-1; i++){
            if(data.attribute(i).isString()){
                System.out.println(i+" contains string values.");
            }
        }
        // //No String values found

        // //Removing attributes with constant values
        // for(int i=1; i<data.numAttributes()-1; i++){
        //     System.out.println("Unique values in Attribute "+i+":" + data.numDistinctValues(i));
        // }
        data.deleteAttributeAt(30);
        data.deleteAttributeAt(9);
        data.deleteAttributeAt(7);
        data.deleteAttributeAt(4);
        // //Constant attributes removed!

        
        // //Checking for nominal features
        for(int i = 1; i<data.numAttributes()-1; i++){
            if(data.attribute(i).isNominal()){
                System.out.println(i+" is nominal.");
            }
        }
        // //No nominal features were found!

        // //Handling Null Values    
        // for(int i=1; i<data.numAttributes()-1; i++){
        //     System.out.println(i+" "+data.attributeStats(i));
        // }
        // //Null values are present in attribute(4)
        ReplaceMissingValues replace = new ReplaceMissingValues();
        replace.setInputFormat(data);
        data = Filter.useFilter(data, replace);
        // // Null values are replaced with mean


        // //Removing features(attributes) with high correlation
        // checkCorrelation(data);
        data.deleteAttributeAt(35);
        data.deleteAttributeAt(34);
        data.deleteAttributeAt(29);
        data.deleteAttributeAt(16);
        data.deleteAttributeAt(10);
        // checkCorrelation(data);
        // //Highly correlated features removed
        

        // //Choosing attributes based on Information Gain(Dimensionality Reduction)
        // //The threshold is selected based on the research work done by Prasetiyowati et. al 
        // //(https://doi.org/10.1186/s40537-021-00472-4)  
        InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
        Ranker search = new Ranker();
        search.setOptions(new String[] { "-T", "0.028" });	// information gain threshold
        AttributeSelection attSelect = new AttributeSelection();
        attSelect.setEvaluator(evaluator);
        attSelect.setSearch(search);
        attSelect.setRanking(true);
        attSelect.SelectAttributes(data);
        data = attSelect.reduceDimensionality(data);
        System.out.println(attSelect.toResultsString());

        // //class balance & sampling
        // System.out.println(data.attributeStats(data.numAttributes()-1));
        // // Class imbalance is noted; class 0: 275 & class 1: 122

        // //Modelling
        data.setClassIndex(data.numAttributes()-1);
        double tp, fp, fn, tn = 0;
        int kFolds = 10;
        for(int k=0; k<kFolds; k++){
            Randomize randomizer = new Randomize();
            randomizer.setInputFormat(data);
            randomizer.setRandomSeed(400);
            data = Filter.useFilter(data, randomizer);
            Instances[] splitData = trainTestSplit(data);
            Instances trainSet = splitData[0];
            Instances testSet = splitData[1];
            // //Minority over sampling
            SMOTE smote = new SMOTE();
            smote.setInputFormat(trainSet);
            trainSet = Filter.useFilter(trainSet, smote);


        }
        

        

        
        // for(int i=0; i<fold; i++){


        // }
        
    }
}
