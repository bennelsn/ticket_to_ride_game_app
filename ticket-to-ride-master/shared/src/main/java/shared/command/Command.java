package shared.command;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import shared.results.Result;

/**
 * Created by paulinecausse on 2/3/18.
 */

/**
 * Command class that creates the command objects
 */
public class Command implements ICommand, Serializable {
    private String _className;
    private String _methodName;
    private Class<?>[] _parmTypes;
    private Object[] _parmValues;
    private int _gameID;

    public Command(String className, String methodName, Class<?>[] parmTypes, Object[] parms) {
        this._className = className;
        this._methodName = methodName;
        this._parmTypes = parmTypes;
        this._parmValues = parms;
        _gameID = -1;
    }

    public Command(String className, String methodName) {
        this._className = className;
        this._methodName = methodName;
        this._parmTypes = new Class[0];
        this._parmValues = new Object[0];
        _gameID = -1;
    }

    public Result execute() throws Exception{
        try {
            Class<?> receiver = Class.forName(_className);
            Method method = receiver.getMethod(_methodName,_parmTypes);
            Object t = receiver.newInstance();
            return (Result) method.invoke(t, _parmValues);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int getGameId() {
        return _gameID;
    }

    @Override
    public void setGameId(int id) {
        _gameID = id;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("_className = " + _className + "\n");
        result.append("_methodName = " + _methodName + "\n");

        result.append("\t_parmTypes = ");

        for(Class parmType : _parmTypes) {
            result.append(parmType + ", ");
        }
        result.delete(result.length()-2, result.length());
        result.append("\n");

        result.append("\t_parmValues = ");
        if(_parmValues == null) {
            result.append("null\n");
        } else {
            for(Object parameter : _parmValues) {
                result.append(parameter);
                result.append("(" + parameter.getClass().getName() + ")");
                result.append(", ");
            }
            result.delete(result.length()-2, result.length());
        }

        return result.toString();
    }

    public static void executeList(List<ICommand> clientCommands) throws Exception {
        for(ICommand command : clientCommands) {
            command.execute();
        }
    }
}
