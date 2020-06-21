package sinacorpdfparser;

import java.util.Locale;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerConfiguration extends Configuration {

	private static FreeMarkerConfiguration instance;
    
    private FreeMarkerConfiguration(){}
    
    public static FreeMarkerConfiguration getInstance(){
        if(instance == null){
        	instance = (FreeMarkerConfiguration) new Configuration();
        	instance.setClassForTemplateLoading(App.class, "/");
        	instance.setDefaultEncoding("UTF-8");
        	instance.setLocale(Locale.US);
        	instance.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        }
        return instance;
    }

}

