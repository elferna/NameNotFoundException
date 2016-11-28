<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.auth.profile.*" %>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>
<%@ page import="com.amazonaws.services.s3.*" %>
<%@ page import="com.amazonaws.services.s3.model.*" %>
<%@ page import="com.amazonaws.services.dynamodbv2.*" %>
<%@ page import="com.amazonaws.services.dynamodbv2.model.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/start.js"></script>
<title>Clueless</title>
<style type="text/css">
	body {background-color: #000000}
	h1, h3, p, div   {color: #40ff00}
</style>
</head>
<body>
	<h1> Welcome to Clueless! </h1>
	<h1><b>-------------------------</b></h1>
	<h3><i>Can you solve the mystery?</i></h3>

    <ul id="messages"></ul>
	<form id="message-form" action="#" method="post">
      	<button type="button" id="newGameBtn" onclick=sendMessage()>New Game</button>
      	<button type="button" id="joinGameBtn">Join Game</button>
	</form>
	<br />
	<h3>Current player connections:</h3>
	<div id="content">
	</div>
</body>
</html>