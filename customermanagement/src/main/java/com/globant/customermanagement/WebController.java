package com.globant.customermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;


@RestController
@RequestMapping("/dynamoDB")
public class WebController 
{
	final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
	DynamoDB dynamoDB = new DynamoDB(ddb);
	
	@RequestMapping("create")
	public String createSampleTeble()
	{
		List<AttributeDefinition> attributeDefinitions= new ArrayList<AttributeDefinition>();
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("N"));

		List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH));
		
		CreateTableRequest request = new CreateTableRequest()
				.withTableName("Test")
		        .withKeySchema(keySchema)
		        .withAttributeDefinitions(attributeDefinitions)
		        .withProvisionedThroughput(new ProvisionedThroughput()
		            .withReadCapacityUnits(5L)
		            .withWriteCapacityUnits(6L));

		try {
		    CreateTableResult result = ddb.createTable(request);
		    System.out.println(result.getTableDescription().getTableName());
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		}
		 
		return "Create Table Successfully";
	}
	
	@RequestMapping("/test")
	public String insertCustomerDetails()
	{
		
		HashMap<String,AttributeValue> item_values = new HashMap<String,AttributeValue>();

			item_values.put("Name", new AttributeValue("Vijaya"));
			item_values.put("ID",new AttributeValue("4"));
			try 
			{
			    ddb.putItem("Customer1", item_values);
			}
			catch (ResourceNotFoundException e)
			{
			    System.err.format("Error: The table \"%s\" can't be found.\n", "Customer1");
			    System.err.println("Be sure that it exists and that you've typed its name correctly!");
			    System.exit(1);
			}
			catch (AmazonServiceException e) 
			{
			    System.err.println(e.getMessage());
			    System.exit(1);
			}
			    
		return "Successfully Inserted data";
	}

	@RequestMapping("/get/{Name}")
	public String getOneCustomerDetails(@PathVariable(name="Name") String Name)
	{
		Table table = dynamoDB.getTable("Customer1");
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("Name",Name);
		Item item = table.getItem(spec);
		return item.toJSONPretty();
	}
	
	@RequestMapping("/delete/{Name}")
	public String getAllCustomerDetails(@PathVariable(name="Name") String Name)
	{
		Table table = dynamoDB.getTable("Customer1");
		table.deleteItem("Name",Name);
		return "Delete item successfully";
	}
}
