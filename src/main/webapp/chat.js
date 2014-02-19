var chatForm = function (){
		Ext.define('users', {
			extend: 'Ext.data.Model',
			fields: ['userName']
			});
            var usersStore = Ext.create('Ext.data.Store', {
				id: 'usersStore',
				model: 'users',
                proxy: {
                    type: 'ajax',
					url: 'usersOnline.jsp',
                    reader: {
                        type: 'json'                     
                    }
                },
				autoLoad: true
            });

	var userOnlinePanel = new Ext.grid.GridPanel({
                title: 'online',
				id: 'userOnlinePanel',
                store: usersStore,
                columns: [
                    { text: '',  dataIndex: 'userName', width: 200 }
                ],
				 frame:false,
                height: 200,
                width: 205
                //renderTo: Ext.getBody()
            });

			
	Ext.define('Chat', {
			extend: 'Ext.data.Model',
			fields: ['message']
			});
            var chatStore = Ext.create('Ext.data.Store', {
				id: 'chatStore',
				model: 'Chat',
                proxy: {
                    type: 'ajax',
					url: 'messages.jsp',
                    reader: {
                        type: 'json'                     
                    }
                },
				autoLoad: true
            });

	var chatPanel = new Ext.grid.GridPanel({
                title: 'chat',
				id: 'chatPanel', 
                store: chatStore,
                columns: [                   
                    { text: '', dataIndex: 'message', width: 400 }
                    ],
				 frame:false,
                height: 200,
                width: 405
                //renderTo: Ext.getBody()
            });
			
		var messageField = new Ext.form.TextField({
            fieldLabel:'message',
            name:'messageField',
            //emptyText:'������� ���� ���������',
            width:400
		});	
	
		var btnSendMsg = Ext.create('Ext.Button', {
								text: 'Send',
								renderTo: Ext.getBody(),
								handler: function() {
									Ext.Ajax.request({
                                                    url: 'messages.jsp?userName='+userName+'&message='+messageField.getValue(),
                                                    method: 'GET',
                                                    scripts:true,
                                                    success: function (response) {
														Ext.getCmp('chatPanel').getStore().load();
                                                        }
                                                                        });
								}
		});
		
				var btnSendPrivateMsg = Ext.create('Ext.Button', {
								text: 'Send private message',
								renderTo: Ext.getBody(),
								handler: function() {
								
									var selectedRecord = Ext.getCmp('userOnlinePanel').getSelectionModel().getSelection()[0];
									var recipientName = selectedRecord.get('userName');
								privateChatWindow(recipientName).show();
		}
	});
			
			
		new Ext.form.FormPanel({ // ������������� ������ � ������.
			
            waitMsgTarget  : true
            ,frame         : false
            ,monitorValid  : true
            ,renderTo: Ext.getBody() // ��������� ��������� � ��������� � ID='ex0'
			,id: 'chat-form'   // ID ������. �� ����������� ��� ������� �����

			,layout: {
				type: 'table',            
				columns: 3
			}
			,items: [
			chatPanel,
			userOnlinePanel,
			btnSendPrivateMsg,
			messageField,			
			btnSendMsg
			]           // ������ ����� �����
				
					
	});

		setInterval(function() {
		var store = Ext.getCmp('chatPanel').getStore();
		store.load();
		}, 5000);
	setInterval(function() {
		var store = Ext.getCmp('userOnlinePanel').getStore();
		store.load();
		}, 10000);
	
	var win=null;
	
		setInterval(function() {
				Ext.Ajax.request({
					url: 'messagesPrivate.jsp?messageFrom='+userName,
					method: 'GET',
					scripts:true,
					success: function (response) {
                        var data = Ext.decode(response.responseText);
                        
                       if(win==null){    
						if(data[0].messageTo==userName){
							win = privateChatWindow(data[0].messageFrom).show();
						}
                       }
					}
				});
		}, 5000);
};