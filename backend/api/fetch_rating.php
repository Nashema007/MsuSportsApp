<?php
	
	require('../utilities/DbConnector.php');
	
	class Rating {
		
		function getOneRating() {
			$dbconn = new DbConnector();
			$con = $dbconn->con();
			
			$rating = $con->query("SELECT * FROM rating where rate='1'");
			$values = $rating->num_rows;
			
			return $values;
			
		}
		
		function getTwoRating() {
			$dbconn = new DbConnector();
			$con = $dbconn->con();
			$rating = $con->query("SELECT * FROM rating where rate='2'");
			$values = $rating->num_rows;
			
			return $values;
			
		}
		
		function getThreeRating() {
			$dbconn = new DbConnector();
			$con = $dbconn->con();
			$rating = $con->query("SELECT * FROM rating where rate='3'");
			$values = $rating->num_rows;
			
			return $values;
			
		}
		
		function getFourRating() {
			$dbconn = new DbConnector();
			$con = $dbconn->con();
			$rating = $con->query("SELECT * FROM rating where rate='4'");
			$values = $rating->num_rows;
			
			return $values;
			
		}
		
		function getFiveRating() {
			$dbconn = new DbConnector();
			$con = $dbconn->con();
			$rating = $con->query("SELECT * FROM rating where rate='5'");
			$values = $rating->num_rows;
			
			return $values;
			
		}
	}
	
	$rates = new Rating();
	
	$rateValues = array();
	array_push($rateValues, array(
			"first" => $rates->getOneRating(),
			"second"=>$rates->getTwoRating(),
			"third"=>$rates->getThreeRating(),
			"fourth"=>$rates->getFourRating(),
			"fifth"=>$rates->getFiveRating()
		)
	);
	
	
	
	echo json_encode($rateValues);
	