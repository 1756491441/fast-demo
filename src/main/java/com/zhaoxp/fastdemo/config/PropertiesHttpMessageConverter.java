package com.zhaoxp.fastdemo.config;

import com.zhaoxp.fastdemo.domain.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @ClassName PropertiesHttpMessageConverter
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/12/17 22:08
 * @Version 1.0
 **/
public class PropertiesHttpMessageConverter extends AbstractHttpMessageConverter<Person> {

    public PropertiesHttpMessageConverter(){
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(Person.class);
    }

    @Override
    protected Person readInternal(Class<? extends Person> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream inputStream = httpInputMessage.getBody();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream));

        Person person = new Person();
        person.setId(Integer.valueOf(properties.getProperty("person.id")));
        person.setName(properties.getProperty("person.name"));

        return person;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        OutputStream out = httpOutputMessage.getBody();

        Properties properties = new Properties();

        properties.setProperty("person.id", String.valueOf(person.getId()));
        properties.setProperty("person.name", person.getName());

        properties.store(new OutputStreamWriter(out, getDefaultCharset()), "write by web server");
    }
}
