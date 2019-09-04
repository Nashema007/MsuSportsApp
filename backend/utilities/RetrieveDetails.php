<?php

	
	require '../utilities/DbConnector.php';
	require '../model/SportModel.php';
	
	class RetrieveDetails {
		
		
		
		
		function sports (){
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			$sportModal = new SportModel();
			
			$soccer = $con->query('SELECT * FROM soccer');
			$sportModal->setSoocer($soccer->num_rows);
			
			$basketball = $con->query('SELECT *FROM basketball');
			$sportModal->setBasketball($basketball->num_rows);
			
			$hockey = $con->query('SELECT * FROM hockey ');
			$sportModal->setHockey($hockey->num_rows);
			
			
			return array(
				"soccer"=>$sportModal->getSoocer(),
				"basketball"=>$sportModal->getBasketball(),
				"hockey"=>$sportModal->getHockey()
			);
			
			
		}
		
		function studentsSoccer(){
			
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM soccer ");//where first_team='0'");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"surname"=>$row['surname'],
					"gender"=>$row['gender'],
					"level"=>$row['level']));
			}
			
			 return $values;
		}
		
		function chooseStudentsSoccer(){
			
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM soccer where first_team='0'");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"surname"=>$row['surname'],
					"gender"=>$row['gender'],
					"level"=>$row['level']));
			}
			
			return $values;
		}
		
		function viewStudentsSoccer(){
			
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM soccer where first_team='true'");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"surname"=>$row['surname'],
					"gender"=>$row['gender'],
					"level"=>$row['level']));
			}
			
			return $values;
		}
		
		function studentsBasketBall(){
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM basketball");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"gender"=>$row['gender'],
					"surname"=>$row['surname'],
					"level"=>$row['level']));
			}
			
			return $values;
		}
		
		function chooseStudentsBasketBall(){
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM basketball where first_team='0'");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"gender"=>$row['gender'],
					"surname"=>$row['surname'],
					"level"=>$row['level']));
			}
			
			return $values;
		}
		
		function viewStudentsBasketBall(){
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM basketball where first_team='true'");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"gender"=>$row['gender'],
					"surname"=>$row['surname'],
					"level"=>$row['level']));
			}
			
			return $values;
		}
		
		function studentHockey(){
			
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM hockey");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"gender"=>$row['gender'],
					"surname"=>$row['surname'],
					"level"=>$row['level']));
			}
			
			return $values;
		}
		
		function chooseStudentHockey(){
			
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM hockey where first_team ='0'");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"gender"=>$row['gender'],
					"surname"=>$row['surname'],
					"level"=>$row['level']));
			}
			
			return $values;
		}
		
		function viewStudentHockey(){
			
			$dbcon = new DbConnector();
			$con = $dbcon->con();
			
			$students = $con->query("SELECT * FROM hockey where first_team ='true'");
			$values = array();
			while ($row = mysqli_fetch_array($students))    {
				
				array_push($values,array(
					"regnumber"=>$row['regnumber'],
					"firstname"=>$row['fname'],
					"gender"=>$row['gender'],
					"surname"=>$row['surname'],
					"level"=>$row['level']));
			}
			
			return $values;
		}
		
		
	}