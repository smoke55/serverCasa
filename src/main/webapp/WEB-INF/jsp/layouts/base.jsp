<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!doctype html> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<tiles:insertAttribute name="head" />
</head>
<body>
<table>
  <tr>
    <td colspan="2">
      <tiles:insertAttribute name="header" />
    </td>
  </tr>
  <tr>
    <td>
      <tiles:insertAttribute name="menu" />
    </td>
    <td>
      <tiles:insertAttribute name="body" />
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <tiles:insertAttribute name="footer" />
    </td>
  </tr>
</table>
</body>
</html>
