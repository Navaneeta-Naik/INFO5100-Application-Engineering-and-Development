/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.analytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lab7.entities.Comment;
import lab7.entities.Post;
import lab7.entities.User;

/**
 *
 * @author hp
 */
public class AnalysisHelper 
{
    // find user with Most Likes
    public void getUserWithMostLikes()
    {
        Map<Integer, Integer> userLikesCount = new HashMap<>();
        Map<Integer, User> users = DataStore.getInstance().getUsers();
    
        for (User user : users.values()) {
            for (Comment c : user.getComments()) {
                int likes = 0;
                if (userLikesCount.containsKey(user.getId())) {
                    likes = userLikesCount.get(user.getId());
                }
                likes += c.getLikes();
                userLikesCount.put(user.getId(), likes);
            }
        }
        int max = 0;
        int maxId = 0;
        for (int id : userLikesCount.keySet()) {
            if (userLikesCount.get(id) > max) {
                max = userLikesCount.get(id);
                maxId = id;
            }
        }
        System.out.println("User with most likes: " + max + "\n" 
            + users.get(maxId));
    }
    
    
    public void getFiveMostLikedComment() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        Collections.sort(commentList, new Comparator<Comment>() {
            @Override 
            public int compare(Comment o1, Comment o2) {
                return o2.getLikes() - o1.getLikes();
            }
        });
        
        System.out.println("5 most likes comments: ");
        for (int i = 0; i < commentList.size() && i < 5; i++) {
            System.out.println(commentList.get(i));
        }
    }
    
    
    // 1. Average Number of Likes Per Comment
    
    public void getAverageNumberOfLikesPerComment() {
        Map<Integer, Comment> comments = DataStore.getDataStore().getComments();
        Map<Integer, Integer> averageNumberOfLikes = new HashMap<Integer, Integer>();
        int totalLikes = 0;
        for (Comment comment : comments.values()) {
            totalLikes = totalLikes + comment.getLikes();
        }

        float averageLikes = totalLikes / comments.size();
        System.out.println("1. Average number of likes per comment " + averageLikes);
    }
    
    
    
    //2. Post with most liked comments
    public void getMostCommentLikedPost(){
        Map<Integer, Integer> postCommentLikes= new HashMap<Integer, Integer>();
        Map<Integer,User> users= DataStore.getInstance().getUsers();
        Map<Integer,Post> post= DataStore.getInstance().getPosts();
        Map<Integer, Comment> comment= DataStore.getInstance().getComments();
        
        for(Post p: post.values()){
            for (Comment c : p.getComments()){
                int likes=0;
                if(postCommentLikes.containsKey(p.getPostId())){
                    likes= postCommentLikes.get(p.getPostId());
                }
                likes=likes + c.getLikes();
                postCommentLikes.put(p.getPostId(), likes);
            }
        }
        int max =0;
        int maxId=0;
        for(int id : postCommentLikes.keySet()){
            if(postCommentLikes.get(id)>max){
                max= postCommentLikes.get(id);
                maxId=id;
            }
        }
        System.out.println("2. Post with most liked comments: " + max + "\n" + post.get(maxId) );
    }
    
    
    
    //3. Post with most comments
    public void postwithMostComments(){
    Map<Integer, Integer> postwithMostComments = new HashMap();
    Map<Integer, Comment> comments = DataStore.getInstance().getComments();
    
    
    for(Comment c : comments.values()){
        int likes = 0;
        
        if(postwithMostComments.containsKey(c.getPostId())){
            likes += postwithMostComments.get(c.getPostId());
        }
        likes += c.getLikes();
        postwithMostComments.put(c.getPostId(), likes);
    }
    
    System.out.println("post and comments");
    System.out.println(comments.values());
    System.out.println(postwithMostComments);
    
    int max = 0;
    int maxId = 0;
    for(int id : postwithMostComments.keySet()){
        if(postwithMostComments.get(id) > max){
            
            max = postwithMostComments.get(id);
            maxId = id;
        }
    }
    System.out.println("3. Post with most comments:\n Post id:"+ maxId+"\nNumber of comments:"+max);
}
    

    
// 4.Top five inactive users based on total posts number.
    
    public void getFiveInactiveUsersPosts(){
        Map<Integer,User> users= DataStore.getInstance().getUsers();
        Map<Integer,Post> post= DataStore.getInstance().getPosts();
        Map<Integer, Integer> userBasedOnPost =new HashMap();
        for (User user: users.values()){
            int postCount =0;
            postCount = user.getPosts().size();
            userBasedOnPost.put(user.getId(), postCount);
        }
        List<Map.Entry<Integer, Integer>> list=
                new LinkedList<>(userBasedOnPost.entrySet());
        
    
        
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>(){
        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
            return o1.getValue() - o2.getValue();
        }
    });
        System.out.println("4. Top Five inactive users based on total posts number : ");
        for(int i =0; i< list.size() && i<5; i++){
            System.out.println(users.get(list.get(i).getKey()) +"Total :" + list.get(i).getValue());
        }
    }
    
    
    
    // 5. Top 5 inactive users based on total comments they created:
    public void inactiveUsersBasedOnComments(){
    
    Map<Integer,User> users = DataStore.getInstance().getUsers();
    
    List<User> usersCommentList = new ArrayList<>(users.values());

    
    
    Collections.sort(usersCommentList, new Comparator<User>(){
    @Override
    public int compare(User u1, User u2){
       return u1.getComments().size() - u2.getComments().size();
    }
    });
   System.out.println("5. Top five inactive users based on comments they created:");
   for(int i = 0; i<usersCommentList.size() && i <5; i++){
       System.out.println(usersCommentList.get(i));
   }

}
    
    
    //6. Top 5 inactive users overall
    
         public void fiveUserInactiveAll(){ 
       
      Map<Integer , Integer> allInactive = new HashMap();
      Map<Integer,Post> post1 = DataStore.getInstance().getPosts(); 
      Map<Integer,User> user1 = DataStore.getInstance().getUsers();      
       for(User users : user1.values())
    {
        allInactive.put(users.getId(), users.getComments().size());
    }
 
       int d = 0;
       int c = 0;
       for(Post posts : post1.values())
    {
              if(allInactive.containsKey(posts.getUserId()))
        {
           d = posts.getUserId();
           c = allInactive.get(posts.getUserId());
           
           allInactive.put(d, ++c);
            
        }  
              
        else
                  allInactive.put(posts.getUserId(),1);
    }
       
       System.out.println(allInactive.entrySet());
       
      Set<Map.Entry<Integer, Integer>> set = allInactive.entrySet();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(set);
        Collections.sort( list, new Comparator<Map.Entry<Integer, Integer>>()
        {
            public int compare( Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2 )
            {
                return o1.getValue() - o2.getValue();
            }
        } );
        
      System.out.println("6. Inactive users overall");
        int c1 = 0;
        for(Map.Entry<Integer, Integer> entry:list){
            c1++;
            if(c1<6)
            {       
            System.out.println(entry.getKey()+"=="+entry.getValue());
            }
            } 
   }
    /* 7. Top 5 proactive users overall */
         
          public void proActiveUserAll(){ 
       
      Map<Integer , Integer> allActive = new HashMap();
     
      Map<Integer,Post> post1 = DataStore.getInstance().getPosts(); 
     
      Map<Integer,User> user1 = DataStore.getInstance().getUsers();
     
      
       for(User users : user1.values())
    {
        allActive.put(users.getId(), users.getComments().size());
    }
       
        
       int d = 0;
       int c = 0;
       for(Post posts : post1.values())
    {
              if(allActive.containsKey(posts.getUserId()))
        {
           d = posts.getUserId();
           c = allActive.get(posts.getUserId());
           
           allActive.put(d, ++c);
            
        }  
              
        else
                  allActive.put(posts.getUserId(),1);
    }
      System.out.println(allActive.entrySet()); 
       
      Set<Map.Entry<Integer, Integer>> set = allActive.entrySet();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(set);
        Collections.sort( list, new Comparator<Map.Entry<Integer, Integer>>()
        {
            public int compare( Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2 )
            {
                return o2.getValue() - o1.getValue();
            }
        } );

      System.out.println("7. Proactive users overall");
        int c1 = 0;
        for(Map.Entry<Integer, Integer> entry:list){
            c1++;
            if(c1<6)
            {       
            System.out.println(entry.getKey()+"=="+entry.getValue());
            }
            } 
   }

         
         
}
