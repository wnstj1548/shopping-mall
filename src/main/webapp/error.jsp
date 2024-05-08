<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
    <head>
        <title>Error Page</title>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <table>
            <tbody>
                <tr>
                    <th>status_code</th>
                    <td><c:out value ="${status_code}" default="N/A"/></td>
                </tr>
                <tr>
                    <th>exception_type</th>
                    <td><c:out value ="${exception_type}" default="N/A"/></td>
                </tr>
                <tr>
                    <th>message</th>
                    <td><c:out value ="${message}" default="N/A"/></td>
                </tr>
                <tr>
                    <th>exception</th>
                    <td><c:out value ="${exception}" default="N/A"/></td>
                </tr>
                <tr>
                    <th>request_uri</th>
                    <td><c:out value ="${request_uri}" default="N/A"/></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
