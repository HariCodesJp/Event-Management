package dtoclasses;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ClientDto 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private int clientId;
private String clientName;
private long clientContact;
private String clientMail;
private String clientPassword;
@OneToMany(cascade = CascadeType.ALL)
private List<ClientEventDto> clientEvent;
public int getClientId() {
	return clientId;
}
public void setClientId(int clientId) {
	this.clientId = clientId;
}
public String getClientName() {
	return clientName;
}
public void setClientName(String clientName) {
	this.clientName = clientName;
}
public long getClientContact() {
	return clientContact;
}
public void setClientContact(long clientContact) {
	this.clientContact = clientContact;
}
public String getClientMail() {
	return clientMail;
}
public void setClientMail(String clientMail) {
	this.clientMail = clientMail;
}
public String getClientPassword() {
	return clientPassword;
}
public void setClientPassword(String clientPassword) {
	this.clientPassword = clientPassword;
}
public List<ClientEventDto> getClient() {
	return clientEvent;
}
public void setClient(List<ClientEventDto> client) {
	this.clientEvent = client;
}
@Override
public String toString() {
	return "ClientDto [clientId=" + clientId + ", clientName=" + clientName + ", clientContact=" + clientContact
			+ ", clientMail=" + clientMail + ", clientPassword=" + clientPassword + ", clientEvent=" + clientEvent
			+ "]";
}



}
