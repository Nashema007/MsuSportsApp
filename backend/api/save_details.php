<?php
	require '../utilities/DbConnector.php';
	
	$dbcon = new DbConnector();
	$con = $dbcon->con();
	
	$name = $_POST['name'];
	$surname = $_POST['surname'];
	$sport= $_POST['sport'];
	$regnum = $_POST['regnum'];
	$prog = $_POST['degree'];
	$level = $_POST['level'];
	$gender = $_POST['gender'];
	
	
	switch ($sport){
		
		case "soccer":
			$resultsSoccer = $con->query("INSERT INTO soccer (`regnumber`, `level`, `fname`, `surname`, `programme`, `gender`)
			VALUES('".$regnum."','".$level."', '".$name."', '".$surname."', '".$prog."', '".$gender."')");
			
			$updateSoccer = $con->query("UPDATE student SET sport = '".$sport."' WHERE regnumber='".$regnum."'");
			
			checkResult($resultsSoccer);
			break;
		case "basketball":
			$resultsBasketball = $con->query("INSERT INTO basketball (`regnumber`, `level`, `fname`, `surname`, `programme`, `gender`)
			VALUES('".$regnum."','".$level."', '".$name."', '".$surname."', '".$prog."', '".$gender."' )");
			$updateBasketball = $con->query("UPDATE student SET sport = '".$sport."' WHERE regnumber='".$regnum."'");
			checkResult($resultsBasketball);
			break;
		case "hockey":
			$resultsHockey = $con->query("INSERT INTO hockey (`regnumber`, `level`, `fname`, `surname`, `programme`, `gender`)
			VALUES('".$regnum."','".$level."', '".$name."', '".$surname."', '".$prog."', '".$gender."' )");
			$updateHockey = $con->query("UPDATE student SET sport = '".$sport."' WHERE regnumber='".$regnum."'");
			checkResult($resultsHockey);
			break;
			
	}
	
	function checkResult($result){
		$response = array();
		if ($result){
			$code = "successful";
			$message = "Successful update";
			array_push($response, array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		}
		elseif (!$result){
			$code = "failed";
			$message = "Something went wrong with the update";
			array_push($response, array("code"=>$code, "message"=>$message));
			echo json_encode($response);
		}
	}
	
	$con->close();
