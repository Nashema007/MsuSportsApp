<?php
	
	
	class DbConnector{
		
		public function __construct(){
		}
		
		function con(){
			
			$localhost = "localhost";
			$dbname="pos_db";
			$username = "root";
			$password = "";
			
			$conn = new mysqli($localhost, $username, $password, $dbname) or exit ($conn->error);
			
			return $conn;
		}
	}