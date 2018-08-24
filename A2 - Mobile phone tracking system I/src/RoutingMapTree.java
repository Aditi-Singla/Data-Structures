public class RoutingMapTree{
	Exchange root;
	MobilePhoneSet allPhones;
	public RoutingMapTree(){
		root = new Exchange (0);
		allPhones = new MobilePhoneSet();
	}
	public void setRoot(Exchange v){
		root = v;
	}
	public Exchange root(){
		return root;
	} 
	public Boolean isEmpty(){
		return (root==null);
	}
	public Boolean isRoot(Exchange v){
		return (root==v);
	}
	public Boolean containsExchangeId(int a){
		if (root.identifier==a)
			return true;
		else{
			for (int i=0;i<root.children.size();i++){
				if ((root.children.getithChild(i).smallTree()).containsExchangeId(a))
					return true;
			}
		return false;	
		}
	}
	public Exchange returnExchange(int a){
		try{	
			if (containsExchangeId(a)){
				if (root.identifier==a){
					return root;
				}	
				else{
					for (int i=0;i<root.children.size();i++){
						if ((root.children.getithChild(i).smallTree()).containsExchangeId(a))
							return (root.children.getithChild(i).smallTree()).returnExchange(a);	
					}
					return null;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("This exchange doesnot exist!");
			return null;
		}			
	}
	public Boolean containsExchange(Exchange a){
		if (root==a)
			return true;
		else{
			for (int i=0;i<root.children.size();i++){
				if ((root.children.getithChild(i).smallTree()).containsExchange(a))
					return true;
			}
		return false;	
		}
	}
	public void switchOn(MobilePhone a,Exchange b){
		try{	
			if (a.status())
				throw new Exception();
			else{
				a.switchOn();
				a.parent = b;
				Exchange c = b;
				while (!isRoot(c)){
					c.residentSet().mobilePhoneSet.insert(a);
					c = c.parent;		
				}
				if (!getPhoneBoolId(a))
					allPhones.mobilePhoneSet.insert(a);
				c.residentSet().mobilePhoneSet.insert(a);
			}
		}
		catch (Exception e){
			System.out.println("The phone is already switched on!");
			return;
		}	
	}
	public void switchOff(MobilePhone a){
		try{	
			if (!a.status())
				throw new Exception();
			else{
				a.switchOff();
				Exchange c = a.parent;
				while (!isRoot(c)){
					c.residentSet().mobilePhoneSet.delete(a);
					c = c.parent;		
				}
				c.residentSet().mobilePhoneSet.delete(a);
				a.parent = null;
			}
		}
		catch (Exception e){
			System.out.println("The phone is already switched off!");
			return;
		}	
	}
	public Boolean getPhoneBoolId(MobilePhone v){
		for (int i=0;i<allPhones.mobilePhoneSet.size();i++){
			MobilePhone p= (MobilePhone)allPhones.mobilePhoneSet.getithMember(i+1);
			if (p==v) 
				return true;
		}
		return false;
	}
	public Boolean getPhoneBool(int num){
		for (int i=0;i<allPhones.mobilePhoneSet.size();i++){
			MobilePhone p= (MobilePhone)allPhones.mobilePhoneSet.getithMember(i+1);
			if (p.number()==num) {
				return true;
			}
		}
		return false;
	}
	public MobilePhone getPhone(int num) {
		if (getPhoneBool(num)){
			for (int i=0;i<allPhones.mobilePhoneSet.size();i++){
				MobilePhone p=(MobilePhone)allPhones.mobilePhoneSet.getithMember(i+1);
				if (p.number()==num) {
					return (p);
				}
			}
			return null;
		}
		else{
			MobilePhone p = new MobilePhone(num);
			p.switchOff();
			return p;
		}	
	}
	public void addExchange(int p,int n){
		try{	
			if (!containsExchangeId(n)){
				try{	
					if (containsExchangeId(p)){
						Exchange child = new Exchange(n);
						Exchange pa = returnExchange(p);
						pa.children.add(child);
						child.setParent(pa);
					}
					else 
						throw new Exception();
				}
				catch (Exception e){
					System.out.println("No such parent Exchange exists");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("This exchange already exists in the tree!");
			return;
		}		
	}
	public void switchOnMobile(int a,int b) {
		try{
			if (containsExchangeId(b)){
				try{	
					if (returnExchange(b).numChildren()==0){
						switchOn(getPhone(a),returnExchange(b));
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					System.out.println("This exchange is not a base station!");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("This exchange doesnot exist!");
			return;
		}
	}
	public void switchOffMobile(int a){
		try{	
			if (getPhoneBool(a))
				switchOff(getPhone(a));
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("This mobile phone doesn't exist!");
			return;
		}	
	}
	public void queryNthChild(int a,int b){
		try{	
			if (containsExchangeId(a)){
				try{
					if (b<returnExchange(a).numChildren() && b>=0){
						int ans = returnExchange(a).child((returnExchange(a).numChildren())-b-1).identifier;
						System.out.println(ans);
					}
					else
						throw new Exception();	
				}
				catch (Exception e){
					System.out.println("The exchange doesnot contain these many members!");
					return;
				}
			}		
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("This node doesnot exist");
			return;
		}
	}
	public void queryMobilePhoneSet(int a){
		try{
			if (containsExchangeId(a)){
				int n = returnExchange(a).residentSet().mobilePhoneSet.size();
				if (n==0){
					System.out.println("");
				}
				else{
					int i;
					for (i=n;i>1;i--){
						MobilePhone p =(MobilePhone)returnExchange(a).residentSet().mobilePhoneSet.getithMember(i);
						System.out.print(p.number() + " , ");
					}
					MobilePhone p =(MobilePhone)returnExchange(a).residentSet().mobilePhoneSet.getithMember(i);
					System.out.println(p.number());
				}
			}
			else
				throw new Exception();	
		}
		catch (Exception e){
			System.out.println("This Exchange doesn't exist!");
			return;
		}		
	}
	public void performAction(String actionMessage) {
		String[] s = actionMessage.trim().split(" +");
		switch (s[0]){
			case "addExchange": addExchange(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			case "switchOnMobile": switchOnMobile(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			case "switchOffMobile": switchOffMobile(Integer.parseInt(s[1]));break;
			case "queryNthChild": queryNthChild(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			case "queryMobilePhoneSet": queryMobilePhoneSet(Integer.parseInt(s[1]));break;
			default : System.out.println("Action not defined!");
		}
	}
} 