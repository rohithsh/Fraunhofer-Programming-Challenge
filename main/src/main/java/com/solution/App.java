package com.solution;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.ReplaceWithMissingValue;
import weka.filters.unsupervised.attribute.Standardize;
import weka.core.converters.ConverterUtils.DataSource;

import org.apache.commons.math.stat.correlation.*;


public class App 
{

    public static void checkCorrelation( Instances data){
        //Checking the co-relation co-efficient b/w the features(attributes)
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
    public static void main( String[] args ) throws Exception
    {
        DataSource source = new DataSource("data.arff");
        Instances data = source.getDataSet();

        

        //Checking for String values
        for(int i = 1; i<data.numAttributes()-1; i++){
            if(data.attribute(i).isString()){
                System.out.println(i+" contains string values.");
            }
        }
        //No String values found

        // //Removing attributes with constant values
        // for(int i=1; i<data.numAttributes()-1; i++){
        //     System.out.println("Unique values in Attribute "+i+":" + data.numDistinctValues(i));
        // }
        data.deleteAttributeAt(30);
        data.deleteAttributeAt(9);
        data.deleteAttributeAt(7);
        data.deleteAttributeAt(4);
        //Constant attributes removed!

        
        //Checking for nominal features
        for(int i = 1; i<data.numAttributes()-1; i++){
            if(data.attribute(i).isNominal()){
                System.out.println(i+" is nominal.");
            }
        }
        // No nominal features were found!


        // //Removing features(attributes) with high correlation
        // checkCorrelation(data);
        data.deleteAttributeAt(35);
        data.deleteAttributeAt(34);
        data.deleteAttributeAt(29);
        data.deleteAttributeAt(16);
        data.deleteAttributeAt(10);
        // checkCorrelation(data);
        //Highly correlated features removed


        //replacing missing values

        //class balance & sampling
        System.out.println(data.attributeStats(31));
    }
}
