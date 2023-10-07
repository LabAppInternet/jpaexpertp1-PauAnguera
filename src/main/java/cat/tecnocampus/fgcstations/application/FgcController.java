package cat.tecnocampus.fgcstations.application;


import cat.tecnocampus.fgcstations.application.DTOs.DayTimeStartDTO;
import cat.tecnocampus.fgcstations.application.DTOs.FavoriteJourneyDTO;
import cat.tecnocampus.fgcstations.application.DTOs.FriendsDTO;
import cat.tecnocampus.fgcstations.application.exception.UserDoesNotExistsException;
import cat.tecnocampus.fgcstations.persistence.UserRepository;
import cat.tecnocampus.fgcstations.domain.*;
import cat.tecnocampus.fgcstations.persistence.FavoriteJourneyRepository;
import cat.tecnocampus.fgcstations.persistence.FriendRepository;
import cat.tecnocampus.fgcstations.persistence.JourneyRepository;
import cat.tecnocampus.fgcstations.persistence.StationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FgcController {
    private StationRepository stationRepository;
    private UserRepository userRepository;
    private FavoriteJourneyRepository favoriteJourneyRepository;
    private JourneyRepository journeyRepository;
    private FriendRepository friendRepository;

    public FgcController(StationRepository stationRepository, UserRepository userRepository,
                         FavoriteJourneyRepository favoriteJourneyRepository, JourneyRepository journeyRepository,
                         FriendRepository friendRepository) {
        this.stationRepository = stationRepository;
        this.userRepository = userRepository;
        this.favoriteJourneyRepository = favoriteJourneyRepository;
        this.journeyRepository = journeyRepository;
        this.friendRepository = friendRepository;
    }

    public List<Station> getStations() {
        return stationRepository.findAll().stream().toList();
    }

    public Station getStation(String nom) {
        return stationRepository.findByNom(nom);
    }

    public User getUser(String username) {
        //get the user
        User user = userRepository.findByUsername(username);

        //get the user's favorite journey
        user.setFavoriteJourneyList(favoriteJourneyRepository.findByUserId(username));

        return user;
    }

    public List<User> getUsers() {
        //get the users
        List<User> users = userRepository.findAll().stream().toList();

        //get the users' favorite journeys
        users.forEach(u -> u.setFavoriteJourneyList(favoriteJourneyRepository.findByUserId(u.getUsername())));

        return users;
    }

    public boolean existsUser(String username) {
        return userRepository.existsUser(username);
    }

    public void addUserFavoriteJourney(String username, FavoriteJourneyDTO favoriteJourneyDTO) {
        FavoriteJourney favoriteJourney = convertFavoriteJourneyDTO(favoriteJourneyDTO);
        saveFavoriteJourney(favoriteJourney, username);
    }

    private void saveFavoriteJourney(FavoriteJourney favoriteJourney, String username) {
        String journeyId = saveJourneyIfDoesNotExist(favoriteJourney.getJourney());
        favoriteJourney.getJourney().setId(journeyId);
        favoriteJourneyRepository.save(favoriteJourney);
    }

    private String saveJourneyIfDoesNotExist(Journey journey) {
        String journeyId = journeyRepository.getJourneyId(journey.getOrigin(),journey.getDestination());
        if (journeyId.equals("-1")) {
            journeyId = UUID.randomUUID().toString();
            journey.setId(journeyId);
            journeyRepository.save(journey);
        }
        return journeyId;
    }

    public List<FavoriteJourney> getFavoriteJourneys(String username) {
        if (!existsUser(username)) {
            UserDoesNotExistsException e = new UserDoesNotExistsException("user " + username + " doesn't exist");
            e.setUsername(username);
            throw e;
        }
        return favoriteJourneyRepository.findByUserId(username);
    }

    private FavoriteJourney convertFavoriteJourneyDTO(FavoriteJourneyDTO favoriteJourneyDTO) {
        FavoriteJourney favoriteJourney = new FavoriteJourney();
        favoriteJourney.setId(UUID.randomUUID().toString());
        Journey journey = new Journey(stationRepository.findByNom(favoriteJourneyDTO.getOrigin()),
                                      stationRepository.findByNom(favoriteJourneyDTO.getDestination()),
                                      "empty id");
        favoriteJourney.setJourney(journey);

        List<DayTimeStart> dayTimeStarts = favoriteJourneyDTO.getDayTimes().stream().map(this::convertDayTimeStartDTO).collect(Collectors.toList());
        favoriteJourney.setDateTimeStarts(dayTimeStarts);

        return favoriteJourney;
    }

    private DayTimeStart convertDayTimeStartDTO(DayTimeStartDTO dayTimeStartDTO) {
        return new DayTimeStart(dayTimeStartDTO.getDayOfWeek(), dayTimeStartDTO.getTime(), UUID.randomUUID().toString());
    }

    public Friends getUserFriends(String username) {
        return friendRepository.findFriendsByUsername(username);
    }

    public List<Friends> getAllUserFriends() {
        return friendRepository.findAll().stream().toList();
    }

    public void saveFriends(FriendsDTO friendsDTO) {
        if (!existsUser(friendsDTO.getUsername())) {
            UserDoesNotExistsException e = new UserDoesNotExistsException("User " + friendsDTO.getUsername() + " doesn't exist");
            e.setUsername(friendsDTO.getUsername());
            throw e;
        }

        Friends friends = convertFriendsDTO(friendsDTO);
        friendRepository.save(friends);
    }

    private Friends convertFriendsDTO(FriendsDTO friendsDTO) {
        Friends friends = new Friends();
        List<String> friendsList = new ArrayList<>();
        friends.setUsername(friendsDTO.getUsername());
        friends.setFriends(friendsDTO.getFriends());
        return friends;
    }
}
