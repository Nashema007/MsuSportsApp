<?php
      require '../utilities/DbConnector.php';
      
      $dbcon = new DbConnector();
      $con = $dbcon->con();
      
      
      $results = $con->query("SELECT * FROM programs");
      $response = array();
      
      while ($row=$results->fetch_array()){
      	
      	array_push($response, array("program_name" => $row["program_name"]));
      	
      }
      
      echo json_encode($response);
      $con->close();