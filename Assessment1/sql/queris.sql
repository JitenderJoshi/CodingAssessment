create table Tb_Batch(
	Batch_Id int NOT NULL,
	Requestor varchar(10) NOT NULL,
	Request_Date_Time date NOT NULL,
	Request_Status varchar(10) NOT NULL,
	Reqeust_For_System varchar(10) NOT NULL,
	primary key (Batch_Id)
);

create table Tb_Batch_Item(
	Batch_Id int NOT NULL,
	Item  int NOT NULL,
	Name nvarchar2(30) NOT NULL,
	Email varchar(50) NOT NULL,
	Init_Password varchar(30) NOT NULL,
	Role varchar(10) NOT NULL,
	Reason_For_Access nvarchar2(100) NOT NULL,
	primary key (Batch_Id, Item),
	foreign key (Batch_Id) references Tb_Batch(Batch_Id)
) ;

INSERT INTO Tb_Batch (Batch_Id, Requestor, Request_Date_Time, Request_Status, Reqeust_For_System) VALUES (518, 'bmore', TO_DATE('10/27/2013 12:34:23 PM', 'mm/dd/yyyy hh12:mi:ss AM'), 'Queued', 'ClinOp');


select Batch_Id, Requestor,  to_char(Request_Date_Time,'mm/dd/yyyy hh12:mi:ss AM'), Request_Status, Reqeust_For_System from Tb_Batch;


INSERT INTO Tb_Batch_Item (Batch_Id, Item, Name, Email, Init_Password, Role, Reason_For_Access) VALUES (518, 1, 'Susan Smith','ssmith@comany1.com','susan12%#?','SuperUser','Because I am "cool", I can do whatever I want.');

INSERT INTO Tb_Batch_Item (Batch_Id, Item, Name, Email, Init_Password, Role, Reason_For_Access) VALUES (518, 2, 'Alex O''Connor','alexoconnor@univ1.edu','itsuniv1','ReadOnly','I need to access report for budget < 1M $');

INSERT INTO Tb_Batch_Item (Batch_Id, Item, Name, Email, Init_Password, Role, Reason_For_Access) VALUES (518, 3, 'John J. Peterson','john.p@comany2.com','J.Pe1234!','Auditor','Access to 1) all reports; 2) server system logs for "Audit" and [app]_Access_Log');

INSERT INTO Tb_Batch_Item (Batch_Id, Item, Name, Email, Init_Password, Role, Reason_For_Access) VALUES (518, 4, 'Chen, Mei ??','chehmei12@123.com','<:-)>{;=0}','ReadOnly','??????????');

commit;

 


 