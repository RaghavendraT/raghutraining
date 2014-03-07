package com.commodities.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.commodities.common.CommodityData;


@Path("/commodityService")
public class CommodityService {
static PersistenceManagerFactory pmf = JDOHelper
.getPersistenceManagerFactory("datanucleus.properties");


@POST
@Path("/add")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response Creating(CommodityData CommodityData) {
	PersistenceManager mgr = pmf.getPersistenceManager();
	Transaction tx = mgr.currentTransaction();

	try {

		tx.begin();
		mgr.makePersistent(CommodityData);
		tx.commit();
	} finally {
		if (tx.isActive()) {
			tx.rollback();
		}
		mgr.close();
	}
	String result = "Track saved : " + CommodityData;
	return Response.status(201).entity(result).build();
}

@PUT
@Path("/update")
@Consumes(MediaType.APPLICATION_JSON)
public Response UpdateEmployee(@QueryParam("id") int eid, CommodityData commodityData) {
	PersistenceManager pm = pmf.getPersistenceManager();
	Transaction tx = pm.currentTransaction();
	String result = null;
	try {
		tx.begin();
		Query q = pm.newQuery(CommodityData.class, "id ==" + commodityData.getId());
		@SuppressWarnings("unchecked")
		List<CommodityData> e1 = (List<CommodityData>) q.execute();

		for (CommodityData e2 : e1) {
			e2.setName(commodityData.getName());
			e2.setPrice(commodityData.getPrice());
			e2.setType(commodityData.getType());
			result = "Track saved : " + e2;
		}
		tx.commit();
	} finally {
		if (tx.isActive()) {
			tx.rollback();
		}
		pm.close();
	}
	return Response.status(200).entity(result).build();

}


@DELETE
@Path("/delete")
@Consumes(MediaType.APPLICATION_JSON)
public Response Deleting(@QueryParam("id") int id) {
	PersistenceManager pm = pmf.getPersistenceManager();
	Transaction tx = pm.currentTransaction();
	String result = null;
	try {
		tx.begin();
		Query q = pm.newQuery(CommodityData.class, "id ==" + id);
		@SuppressWarnings("unchecked")
		List<CommodityData> e1 = (List<CommodityData>) q.execute();
		pm.deletePersistentAll(e1);
		result = "Track deleted with CommodityData id: " + e1;
		tx.commit();
	} finally {
		if (tx.isActive()) {
			tx.rollback();
		}
		pm.close();
	}
	return Response.status(200).entity(result).build();
}

@GET
@Path("/getById")
@Produces(MediaType.APPLICATION_JSON)
public Response readCommodityData(@QueryParam("id") int id) {
	PersistenceManager pm = pmf.getPersistenceManager();
	Transaction tx = pm.currentTransaction();
	try {
		tx.begin();
		Query q = pm.newQuery(CommodityData.class, "id ==" + id);
		CommodityData e = (CommodityData) pm.detachCopy(q.execute());

		tx.commit();
		if (e != null)
			return Response.status(200).entity(e).build();
		else
			return Response.status(200).entity("Not found").build();

	} finally {
		if (tx.isActive()) {
			tx.rollback();
		}
		pm.close();
	}
}

@GET
@Path("/getByName")
@Produces(MediaType.APPLICATION_JSON)
public Response readCommodityData(@QueryParam("name") String name) {
	PersistenceManager pm = pmf.getPersistenceManager();
	Transaction tx = pm.currentTransaction();
	try {
		tx.begin();
		Query q = pm.newQuery(CommodityData.class, "name ==" + name);
		CommodityData e = (CommodityData) pm.detachCopy(q.execute());

		tx.commit();
		if (e != null)
			return Response.status(200).entity(e).build();
		else
			return Response.status(200).entity("Not found").build();

	} finally {
		if (tx.isActive()) {
			tx.rollback();
		}
		pm.close();
	}
}

@GET
@Path("/list")
@Produces(MediaType.APPLICATION_JSON)
public Response getListOfAllCommodityDatas() {
	PersistenceManager pm = pmf.getPersistenceManager();

	Transaction tx = pm.currentTransaction();
	try {

		tx.begin();
		Query q = pm.newQuery(CommodityData.class);

		@SuppressWarnings("unchecked")
		Collection<CommodityData> collection = (Collection<CommodityData>) q
				.execute();
		List<CommodityData> ListOfCommodityData = (List<CommodityData>) pm
				.detachCopyAll(collection);
		tx.commit();
		GenericEntity<List<CommodityData>> genericEntity = new GenericEntity<List<CommodityData>>(
				ListOfCommodityData) {
		};
		return Response.status(Response.Status.OK.getStatusCode())
				.entity(genericEntity).build();

	} finally {
		if (tx.isActive()) {
			tx.rollback();
		}
		pm.close();
	}

}

@GET
@Path("/listOfExp")
@Produces(MediaType.APPLICATION_JSON)
public Response getListOfExpCommodityDatas() {
	PersistenceManager pm = pmf.getPersistenceManager();
	Transaction tx = pm.currentTransaction();
	try {
 
		tx.begin();
		Query q = null ; 
		Date date =  CommodityData.getCurrentDate() ; 
		if ( date!=null)
		 q = pm.newQuery(CommodityData.class,"date!=null && "+"date <="+ CommodityData.getCurrentDate());

		
		Collection<CommodityData> collection = (Collection<CommodityData>) q.execute();
		List<CommodityData> ListOfCommodityData = (List<CommodityData>) pm.detachCopyAll(collection);
		tx.commit();
		GenericEntity<List<CommodityData>> genericEntity = new GenericEntity<List<CommodityData>>(ListOfCommodityData) {};
		
		return Response.status(Response.Status.OK.getStatusCode())
				.entity(genericEntity).build();

	} finally {
		if (tx.isActive()) {
			tx.rollback();
		}
		pm.close();
	}

}

}
