package com.solution;

import weka.core.Instances;


import weka.core.converters.ConverterUtils.DataSource;

import org.apache.commons.math.stat.correlation.*;


public class App 
{

    public static void check_corelation( Instances data){
        //Checking the co-relation co-efficient b/w the features(attributes)
        for(int i=1; i<data.numAttributes()-1; i++){
            for(int j=i+1; j<data.numAttributes()-1; j++){
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

    public static double [][] clear_array(double a[][]){
        for( int i = 0; i < a.length; i++ ){
            a[i] = null;
        }
        return a;
    }
    public static void main( String[] args ) throws Exception
    {
        DataSource source = new DataSource("data.arff");
        Instances data = source.getDataSet();

        // System.out.println("Checking for String values:"+data.checkForStringAttributes());

        // //Removing attributes with constant values
        // for(int i=1; i<data.numAttributes()-1; i++){
        //     System.out.println("Unique values in Attribute "+i+":" + data.numDistinctValues(i));
        // }
        data.deleteAttributeAt(30);
        data.deleteAttributeAt(9);
        data.deleteAttributeAt(7);
        data.deleteAttributeAt(4);
        
        // System.out.println("Constant attributes removed!");
        
        check_corelation(data);

        // //Removing features(attributes) with high co-relation
        // data.deleteAttributeAt(39);
        // data.deleteAttributeAt(38);
        // data.deleteAttributeAt(33);
        // data.deleteAttributeAt(18);
        // data.deleteAttributeAt(13);
        // System.out.println("\n Remaining attributes"+data.numAttributes()+"\n");

        // check_corelation(data);

    }
}
