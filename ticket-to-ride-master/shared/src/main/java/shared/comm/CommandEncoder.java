package shared.comm;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import shared.command.ICommand;
import shared.results.Result;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * Created by paulinecausse on 2/3/18.
 */

public class CommandEncoder {
    private static CommandEncoder _instance = new CommandEncoder();
    private XStream xStream;

    private CommandEncoder(){
        xStream = new XStream(new DomDriver());
    }

    public static Result decodeCommandResults(InputStream inputStream){
        return (Result) _instance.xStream.fromXML(inputStream);
    }

    public static void encodeCommandResults(Result results, OutputStream responseBody) {
        _instance.xStream.toXML(results, responseBody);
    }

    public static void encodeCommand(ICommand command, OutputStream outputStream) {
        _instance.xStream.toXML(command, outputStream);
    }

    public static ICommand decodeCommand(InputStream inputStream) {
        return (ICommand) _instance.xStream.fromXML(inputStream);
    }

    public static void encodeTestResults(Object o, OutputStream outputStream) {
        _instance.xStream.toXML(o, outputStream);
    }

    public static String encodeDBInfo(Object o){
        return _instance.xStream.toXML(o);

    }

    public static Object decodeDBInfo(String input){
        return _instance.xStream.fromXML(input);
    }
}
