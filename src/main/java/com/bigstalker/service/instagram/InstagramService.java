package com.bigstalker.service.instagram;

import java.util.Date;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import org.jinstagram.Instagram;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.UserFeed;

@Stateless
public class InstagramService {

	
	
	
	@Asynchronous
	public void syncInstagram(org.jinstagram.auth.oauth.InstagramService instagramService, Instagram instagram)throws Exception{
		try{
			Date initialDate = new Date();
			System.out.println("-------------------------------------------");
			System.out.println("TOKEN:"+instagram.getAccessToken());
			System.out.println("-------------------------------------------");
			System.out.println("Sync user ="+instagram.getCurrentUserInfo().getData().getUsername());
			MediaFeed feed =  instagram.getUserRecentMedia();
			System.out.println(feed.getData());
	        UserFeed friendList =  instagram.getUserFollowList(instagram.getCurrentUserInfo().getData().getId());
//	        List<Profile> profiles = new ArrayList<Profile>();
//	        System.out.println("total de amigos = "+friendList.getUserList().size());
//	        for(UserFeedData userFeedData:friendList.getUserList()){
//	        	Profile profileSearch = profileService.recoverByUsername(userFeedData.getUserName());
//	        	if(profileSearch == null){
//		        	compositeNewProfile(instagram, profiles, userFeedData);
//	        	}else{
//	        		compositeUpdateProfile(instagram, userFeedData, profileSearch);	
//	        	}	
//	        }
//	        if(!profiles.isEmpty()){
//	        	System.out.println("Executing pending "+instagram.getCurrentUserInfo().getData().getUsername());
//	        	executePending(profiles);
//	        }
//	        Date endDate = new Date();
//	        System.out.println("tempo de execucao ="+utilData.datediff(utilData.SEC_DIFF,endDate,initialDate)+" Segundos");
//	        System.out.println("-------------------------------------------");
//	        
		}catch(Exception e){
			e.printStackTrace();
		}  
	}
}
