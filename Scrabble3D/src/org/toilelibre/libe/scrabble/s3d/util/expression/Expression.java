package org.toilelibre.libe.scrabble.s3d.util.expression;

import java.util.HashMap;
import java.util.Map;

public final class Expression {
    private static char [] priorities = { '%', '/', '*', '-', '+' };

    private Expression () {

    }

    private static Node buildTree (final String expr) {
        final Node n = new Node ();
        String expr2 = expr.trim ();
        while ( (expr2.charAt (0) == '(')
                && (expr2.charAt (expr2.length () - 1) == ')')) {
            expr2 = expr2.substring (1, expr2.length () - 1).trim ();
        }
        final int operator = Expression.operator (expr2);
        if ( (operator == -1) || (operator == 0)) {
            n.setLetter (expr2);
            n.setLeft (null);
            n.setRight (null);
        } else {
            n.setLetter ("" + expr2.charAt (operator));
            n.setLeft (Expression.buildTree (expr2.substring (0, operator)));
            n.setRight (Expression.buildTree (expr2.substring (operator + 1)));
        }
        return n;
    }

    private static double op (final char code, final double op1,
            final double op2) {
        double res = 0;
        switch (code) {
            case '+' :
                res = op1 + op2;
                break;
            case '-' :
                res = op1 - op2;
                break;
            case '*' :
                res = op1 * op2;
                break;
            case '/' :
                res = op1 / op2;
                break;
            case '%' :
                res = op1 % op2;
                break;
            default:
                break;
        }
        return res;
    }

    private static int operator (final String expr) {
        int numParent = 0;
        int pos = -1;
        int priority = -1;
        for (int i = 0 ; i < expr.length () ; i += 1) {
            final int priorityTmp = Expression.priority (expr.charAt (i));
            if (expr.charAt (i) == '(') {
                numParent += 1;
            }
            if (expr.charAt (i) == ')') {
                numParent -= 1;
            }
            if ( (numParent == 0) && (priorityTmp >= priority)) {
                priority = priorityTmp;
                pos = i;
            }
        }
        return pos;
    }

    private static int priority (final char operator) {
        final int moinsdeux = -2;
        int priority = moinsdeux;
        int i = 0;
        while ( (i < Expression.priorities.length) && (priority == moinsdeux)) {
            if (Expression.priorities [i] == operator) {
                priority = i;
            }
            i += 1;
        }
        return priority;
    }

    private static double replaceEnv (final Map<String, Double> env,
            final String id) {
        double res = 0;
        if (env.containsKey (id)) {
            res = env.get (id).doubleValue ();
        } else {
            try {
                res = Double.parseDouble (id);
            } catch (final NumberFormatException nfe) {
                res = 0;
            }
        }
        return res;
    }

    public static double valueOfExpr (final String expr,
            final Map<String, Double> env) {
        return Expression.valueOfTree (Expression.buildTree (expr), env);
    }

    public static double valueOfTree (final Node n) {
        return Expression.valueOfTree (n, new HashMap<String, Double> ());
    }

    public static double valueOfTree (final Node n,
            final Map<String, Double> env) {
        final int moinsdeux = -2;
        if ( (Expression.priority (n.getLetter ().charAt (0)) != moinsdeux)
                && (n.getLeft () != null) && (n.getLeft () != null)) {
            return Expression.op (n.getLetter ().charAt (0),
                    Expression.valueOfTree (n.getLeft (), env),
                    Expression.valueOfTree (n.getRight (), env));
        }
        return Expression.replaceEnv (env, n.getLetter ());
    }
}
