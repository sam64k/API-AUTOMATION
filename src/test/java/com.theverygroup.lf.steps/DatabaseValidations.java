package com.theverygroup.lf.steps;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.Map;

import com.theverygroup.lf.utility.DBoperations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class DatabaseValidations extends DBoperations {
	
	@Given("user connects to FIM DB")
	public void user_connects_to_fim_db() throws SQLException {
		this.connectToFIMdb();
	}
	@Given("I query db {string}")
	public void the_i_query_db(String SQLquery) throws SQLException {
	    // Write code here that turns the phrase above into concrete actions
	    this.exeQuery(SQLquery);
	}
	@Then("I perform database validations")
	public void i_perform_database_validations(io.cucumber.datatable.DataTable dataTable) throws SQLException {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    Map<String,String> validations = dataTable.asMap(String.class,String.class);
	    String actual;
	    for(Map.Entry<String, String> entry:validations.entrySet() ) {
	    	//System.out.println("KEY: "+entry.getKey()+":"+this.getResultSetColumnValue(entry.getKey()));
	    	actual = this.valueExistinResultSet(entry.getKey(), entry.getValue());
	    	assertEquals(entry.getValue(),actual);
	    }
	}

}
