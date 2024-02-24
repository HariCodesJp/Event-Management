package dtoclasses;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClientEventDto
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private int clientEventId;
private int clientEventNoOfPeople;
private LocalDate startdate;
private int clientEventNoOfDays;
private String clientEventLocation;
private double clientEventCost;

@ManyToOne(cascade = CascadeType.ALL)
private ClientDto client;
@OneToMany(cascade = CascadeType.ALL)

private List<ClientServiceDto> clientService;
private EventType event;


public double getClientEventCost() {
	return clientEventCost;
}
public void setClientEventCost(double clientEventCost) {
	this.clientEventCost = clientEventCost;
}

public int getClientEventId() {
	return clientEventId;
}
public void setClientEventId(int clientEventId) {
	this.clientEventId = clientEventId;
}
public int getClientEventNoOfPeople() {
	return clientEventNoOfPeople;
}
public void setClientEventNoOfPeople(int clientEventNoOfPeople) {
	this.clientEventNoOfPeople = clientEventNoOfPeople;
}
public LocalDate getStartdate() {
	return startdate;
}
public void setStartdate(LocalDate startdate) {
	this.startdate = startdate;
}
public int getClientEventNoOfDays() {
	return clientEventNoOfDays;
}
public void setClientEventNoOfDays(int clientEventNoOfDays) {
	this.clientEventNoOfDays = clientEventNoOfDays;
}
public String getClientEventLocation() {
	return clientEventLocation;
}
public void setClientEventLocation(String clientEventLocation) {
	this.clientEventLocation = clientEventLocation;
}
public double getClientEvenCost() {
	return clientEventCost;
}
public void setClientEvenCost(double clientEvenCost) {
	this.clientEventCost = clientEvenCost;
}
public ClientDto getClient() {
	return client;
}
public void setClient(ClientDto client) {
	this.client = client;
}
public List<ClientServiceDto> getClientService() {
	return clientService;
}
public void setClientService(List<ClientServiceDto> clientService) {
	this.clientService = clientService;
}
public EventType getEvent() {
	return event;
}
public void setEvent(EventType event) {
	this.event = event;
}
@Override
public String toString() {
	return "ClientEventDto [clientEventId=" + clientEventId + ", clientEventNoOfPeople=" + clientEventNoOfPeople
			+ ", startdate=" + startdate + ", clientEventNoOfDays=" + clientEventNoOfDays + ", clientEventLocation="
			+ clientEventLocation + ", clientEventCost=" + clientEventCost + ", clientService=" + clientService
			+ ", event=" + event + "]";
}

 


}
