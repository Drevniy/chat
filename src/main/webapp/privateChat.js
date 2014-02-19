function privateChatWindow(nameRecepient){

Ext.define('privateChat', {
			extend: 'Ext.data.Model',
			fields: ['message']
			});
            var privateChatStore = Ext.create('Ext.data.Store', {
				id: 'privateChatStore',
				model: 'privateChat',
                proxy: {
                    type: 'ajax',
					url: 'messagesPrivate.jsp?messageFrom='+userName+'&messageTo='+nameRecepient,
                    reader: {
                        type: 'json'                     
                    }
                },
				autoLoad: true
            });

	var privateChatPanel = new Ext.grid.GridPanel({
                title: '',
				id: 'privateChatPanel', 
                store: privateChatStore,
                columns: [                   
                    { text: '', dataIndex: 'message', width: 400 }
                    ],
				 frame:false,
                height: 200,
                width: 380
                //renderTo: Ext.getBody()
            });

	var privateMessageField = new Ext.form.TextField({
				fieldLabel:'message',
				name:'privateMessageField',
				//emptyText:'Введите ваше сообщение',
				width:380
			});	
	
	var btnSendPrivateMsg = Ext.create('Ext.Button', {
							text: 'Send',
							renderTo: Ext.getBody(),
							handler: function() {
								Ext.Ajax.request({
												url: 'messagesPrivate.jsp?messageFrom='+userName+'&messageTo='+nameRecepient+'&message='+privateMessageField.getValue(),
												method: 'GET',
												scripts:true,
												success: function (response) {
													privateMessageField.setValue('');
													Ext.getCmp('privateChatPanel').getStore().load();
													}
																	});
							}
	});
	
		setInterval(function() {
		var store = Ext.getCmp('privateChatPanel').getStore();
		store.load();
		}, 5000);
	

 return new Ext.window.Window( {
					title: nameRecepient,
					height: 300,
					width: 400,
					layout: 'fit',
					items: [ 
					privateChatPanel,
					privateMessageField,
					btnSendPrivateMsg
					]	
						
									
				});
				
};