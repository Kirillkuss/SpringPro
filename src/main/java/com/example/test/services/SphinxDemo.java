package com.example.test.services;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SphinxDemo {

    private static String ACOUSTIC_MODELS = "file:///D:/Project/TestJavaSphinx/sphinx/ru4/split/msu_ru_zero.cd_cont_2000";
    private static String LANGUAGE_MODEL  = "file:///D:/Project/TestJavaSphinx/sphinx/ru.lm";
    private static String DICTIONARY      = "file:///D:/Project/TestJavaSphinx/sphinx/ru.dic";
    private static String GRAMMAR         = "file:///D:/Project/TestJavaSphinx/sphinx/gram/";
    public static String getRecord( String record ) throws Exception{
        try{
            String response = null;
            Configuration configuration = new Configuration();
            configuration.setAcousticModelPath( ACOUSTIC_MODELS );
            configuration.setDictionaryPath( DICTIONARY );
            configuration.setGrammarPath( GRAMMAR );
            configuration.setGrammarName("digits.grxml");
            configuration.setUseGrammar( true );
            //configuration.setLanguageModelPath( LANGUAGE_MODEL );
            //configuration.setGrammarName( GRAMMAR );
            configuration.setSampleRate(8000);
            StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer( configuration );
            InputStream stream = new FileInputStream(new File("D://Project/TestJavaSphinx/src/main/resources/decoder-test.wav"));
            recognizer.startRecognition(stream);
            SpeechResult result ;
            while ((result = recognizer.getResult()) != null) {
                response = result.getHypothesis();
            }
            recognizer.stopRecognition();
            return response;
        } catch( Exception ex ){
            ex.printStackTrace( System.err );
            return ex.getMessage();
        }
    }
}
