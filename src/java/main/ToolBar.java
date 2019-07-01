/**
 * @author Vasyl Lyashkevych
 */
package main;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static main.DictionaryPane.jTablePlenty;
import static main.DictionaryThing.jTableThingPlenty;
import json.ParseTypeTree;
import static json.ParseTypeTree.typeArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.Tabl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import util.FileManager;


public class ToolBar extends JFrame  {  
    
        public static String[] hSchema = new String [] {
    "Thing", "Action", "AchieveAction", "LoseAction", "TieAction", "WinAction", "AssessAction",
    "ChooseAction", "VoteAction", "IgnoreAction", "ReactAction", "AgreeAction", "DisagreeAction",
    "DislikeAction", "EndorseAction", "LikeAction", "WantAction", "ReviewAction", "ConsumeAction",
    "DrinkAction", "EatAction", "InstallAction", "ListenAction", "ReadAction", "UseAction", "WearAction",
    "ViewAction", "WatchAction", "ControlAction", "ActivateAction", "DeactivateAction", "ResumeAction",
    "SuspendAction", "CreateAction", "CookAction", "DrawAction", "FilmAction", "PaintAction", "PhotographAction",
    "WriteAction", "FindAction", "CheckAction", "DiscoverAction", "TrackAction", "InteractAction",
    "BefriendAction", "CommunicateAction", "AskAction", "CheckInAction", "CheckOutAction", "CommentAction",
    "InformAction", "ConfirmAction", "RsvpAction", "InviteAction", "ReplyAction", "ShareAction", "FollowAction",
    "JoinAction", "LeaveAction", "MarryAction", "RegisterAction", "SubscribeAction", "UnRegisterAction", "MoveAction",
    "ArriveAction", "DepartAction", "TravelAction", "OrganizeAction", "AllocateAction", "AcceptAction", "AssignAction",
    "AuthorizeAction", "RejectAction", "ApplyAction", "BookmarkAction", "PlanAction", "CancelAction", "ReserveAction",
    "ScheduleAction", "PlayAction", "ExerciseAction", "PerformAction", "SearchAction", "TradeAction", "BuyAction",
    "DonateAction", "OrderAction", "PayAction", "PreOrderAction", "QuoteAction", "RentAction", "SellAction", "TipAction",
    "TransferAction", "BorrowAction", "DownloadAction", "GiveAction", "LendAction", "ReceiveAction", "ReturnAction",
    "SendAction", "TakeAction", "UpdateAction", "AddAction", "InsertAction", "AppendAction", "PrependAction", "DeleteAction",
    "ReplaceAction", "CreativeWork", "Article", "NewsArticle", "Report", "ScholarlyArticle", "MedicalScholarlyArticle",
    "SocialMediaPosting", "BlogPosting", "LiveBlogPosting", "DiscussionForumPosting", "TechArticle", "APIReference", "Atlas",
    "Blog", "Book", "Audiobook", "Chapter", "Clip", "MovieClip", "RadioClip", "TVClip", "VideoGameClip", "Collection", "ComicStory",
    "ComicCoverArt", "Comment", "Answer", "Conversation", "Course", "CreativeWorkSeason", "RadioSeason", "TVSeason", "CreativeWorkSeries",
    "BookSeries", "MovieSeries", "Periodical", "ComicSeries", "Newspaper", "RadioSeries", "TVSeries", "VideoGameSeries", "DataCatalog",
    "Dataset", "DataFeed", "Diet", "DigitalDocument", "NoteDigitalDocument", "PresentationDigitalDocument", "SpreadsheetDigitalDocument",
    "TextDigitalDocument", "EnumerationValueSet", "Episode", "RadioEpisode", "TVEpisode", "ExercisePlan", "Game", "VideoGame", "Map",
    "MediaObject", "AudioObject", "Audiobook", "DataDownload", "ImageObject", "Barcode", "MusicVideoObject", "VideoObject", "Message",
    "EmailMessage", "Movie", "MusicComposition", "MusicPlaylist", "MusicAlbum", "MusicRelease", "MusicRecording", "Painting", "Photograph",
    "PublicationIssue", "ComicIssue", "PublicationVolume", "Question", "Quotation", "Recipe", "Review", "ClaimReview", "Sculpture",
    "Series", "SoftwareApplication", "MobileApplication", "VideoGame", "WebApplication", "SoftwareSourceCode", "TVSeason", "TVSeries",
    "Thesis", "VisualArtwork", "CoverArt", "ComicCoverArt", "WebPage", "AboutPage", "CheckoutPage", "CollectionPage", "ImageGallery",
    "VideoGallery", "ContactPage", "ItemPage", "MedicalWebPage", "ProfilePage", "QAPage", "SearchResultsPage", "WebPageElement",
    "SiteNavigationElement", "Table", "WPAdBlock", "WPFooter", "WPHeader", "WPSideBar", "WebSite", "Event", "BusinessEvent", 
    "ChildrensEvent", "ComedyEvent", "CourseInstance", "DanceEvent", "DeliveryEvent", "EducationEvent", "EventSeries", "ExhibitionEvent",
    "Festival", "FoodEvent", "LiteraryEvent", "MusicEvent", "PublicationEvent", "BroadcastEvent", "OnDemandEvent", "SaleEvent",
    "ScreeningEvent", "SocialEvent", "SportsEvent", "TheaterEvent", "VisualArtsEvent", "Intangible", "AlignmentObject", "Audience",
    "BusinessAudience", "EducationalAudience", "MedicalAudience", "Patient", "PeopleAudience", "MedicalAudience", "ParentAudience",
    "BedDetails", "Brand", "BroadcastChannel", "RadioChannel", "TelevisionChannel", "BroadcastFrequencySpecification", "BusTrip", "Class",
    "ComputerLanguage", "DataFeedItem", "Demand", "DigitalDocumentPermission", "EntryPoint", "Enumeration", "ActionStatusType", 
    "BoardingPolicyType", "BookFormatType", "BusinessEntityType", "BusinessFunction", "ContactPointOption", "DayOfWeek", "DeliveryMethod",
    "LockerDelivery", "ParcelService", "DigitalDocumentPermissionType", "EnumerationValue", "EventStatusType", "GamePlayMode",
    "GameServerStatus", "GenderType", "ItemAvailability", "ItemListOrderType", "MapCategoryType", "MedicalEnumeration", "DrugClass",
    "DrugCost", "DrugCostCategory", "DrugPregnancyCategory", "DrugPrescriptionStatus", "InfectiousAgentClass", "MedicalAudience",
    "MedicalDevicePurpose", "MedicalEvidenceLevel", "MedicalImagingTechnique", "MedicalObservationalStudyDesign", "MedicalProcedureType",
    "MedicalSpecialty", "MedicalStudyStatus", "MedicalTrialDesign", "MedicineSystem", "PhysicalExam", "MedicalTrialDesign",
    "MusicAlbumProductionType", "MusicAlbumReleaseType", "MusicReleaseFormatType", "OfferItemCondition", "OrderStatus", "PaymentMethod",
    "PaymentCard", "CreditCard", "PaymentStatusType", "PhysicalActivityCategory", "QualitativeValue", "BedType", "CarUsageType",
    "DriveWheelConfigurationValue", "SteeringPositionValue", "ReservationStatusType", "RestrictedDiet", "RsvpResponseType", "Specialty",
    "MedicalSpecialty", "WarrantyScope", "Flight", "GameServer", "HealthInsurancePlan", "HealthPlanCostSharingSpecification",
    "HealthPlanFormulary", "HealthPlanNetwork", "Invoice", "ItemList", "BreadcrumbList", "OfferCatalog", "JobPosting", "Language",
    "ListItem", "Offer", "AggregateOffer", "Order", "OrderItem", "ParcelDelivery", "Permit", "GovernmentPermit", "ProgramMembership",
    "Property", "PropertyValueSpecification", "Quantity", "Distance", "Duration", "Energy", "Mass", "Rating", "AggregateRating",
    "Reservation", "BusReservation", "EventReservation", "FlightReservation", "FoodEstablishmentReservation", "LodgingReservation",
    "RentalCarReservation", "ReservationPackage", "TaxiReservation", "TrainReservation", "Role", "LinkRole", "OrganizationRole",
    "EmployeeRole", "PerformanceRole", "Seat", "Service", "BroadcastService", "CableOrSatelliteService", "FinancialProduct", "BankAccount",
    "DepositAccount", "CurrencyConversionService", "InvestmentOrDeposit", "DepositAccount", "LoanOrCredit", "CreditCard", "PaymentCard",
    "PaymentService", "FoodService", "GovernmentService", "TaxiService", "ServiceChannel", "StructuredValue", "ContactPoint", "PostalAddress",
    "EngineSpecification", "GeoCoordinates", "GeoShape", "GeoCircle", "InteractionCounter", "MonetaryAmount", "NutritionInformation",
    "OpeningHoursSpecification", "OwnershipInfo", "PriceSpecification", "CompoundPriceSpecification", "DeliveryChargeSpecification",
    "PaymentChargeSpecification", "UnitPriceSpecification", "PropertyValue", "LocationFeatureSpecification", "QuantitativeValue",
    "TypeAndQuantityNode", "WarrantyPromise", "Ticket", "TrainTrip", "MedicalEntity", "AnatomicalStructure", "Bone", "BrainStructure",
    "Joint", "Ligament", "Muscle", "Nerve", "Vessel", "Artery", "LymphaticVessel", "Vein", "AnatomicalSystem", "LifestyleModification",
    "Diet", "PhysicalActivity", "ExercisePlan", "MedicalCause", "MedicalCondition", "InfectiousDisease", "MedicalSignOrSymptom",
    "MedicalSign", "VitalSign", "MedicalSymptom", "MedicalContraindication", "MedicalDevice", "MedicalGuideline", "MedicalGuidelineContraindication",
    "MedicalGuidelineRecommendation", "MedicalIndication", "ApprovedIndication", "PreventionIndication", "TreatmentIndication",
    "MedicalIntangible", "DDxElement", "DoseSchedule", "MaximumDoseSchedule", "RecommendedDoseSchedule", "ReportedDoseSchedule",
    "DrugLegalStatus", "DrugStrength", "MedicalCode", "MedicalConditionStage", "MedicalProcedure", "DiagnosticProcedure", 
    "PalliativeProcedure", "PhysicalExam", "SurgicalProcedure", "TherapeuticProcedure", "MedicalTherapy", "OccupationalTherapy", "PalliativeProcedure",
    "PhysicalTherapy", "RadiationTherapy", "RespiratoryTherapy", "PsychologicalTreatment", "MedicalRiskEstimator", "MedicalRiskCalculator",
    "MedicalRiskScore", "MedicalRiskFactor", "MedicalStudy", "MedicalObservationalStudy", "MedicalTrial", "MedicalTest", "BloodTest", "ImagingTest",
    "MedicalTestPanel", "PathologyTest", "Substance", "DietarySupplement", "Drug", "SuperficialAnatomy", "Organization", "Airline",
    "Corporation", "EducationalOrganization", "CollegeOrUniversity", "ElementarySchool", "HighSchool", "MiddleSchool", "Preschool",
    "School", "GovernmentOrganization", "LocalBusiness", "AnimalShelter", "AutomotiveBusiness", "AutoBodyShop", "AutoDealer", "AutoPartsStore",
    "AutoRental", "AutoRepair", "AutoWash", "GasStation", "MotorcycleDealer", "MotorcycleRepair", "ChildCare", "Dentist", "DryCleaningOrLaundry",
    "EmergencyService", "FireStation", "Hospital", "PoliceStation", "EmploymentAgency", "EntertainmentBusiness", "AdultEntertainment",
    "AmusementPark", "ArtGallery", "Casino", "ComedyClub", "MovieTheater", "NightClub", "FinancialService", "AccountingService",
    "AutomatedTeller", "BankOrCreditUnion", "InsuranceAgency", "FoodEstablishment", "Bakery", "BarOrPub", "Brewery",
    "CafeOrCoffeeShop", "Distillery", "FastFoodRestaurant", "IceCreamShop", "Restaurant", "Winery", "GovernmentOffice", "PostOffice",
    "HealthAndBeautyBusiness", "BeautySalon", "DaySpa", "HairSalon", "HealthClub", "NailSalon", "TattooParlor", "HomeAndConstructionBusiness",
    "Electrician", "GeneralContractor", "HVACBusiness", "HousePainter", "Locksmith", "MovingCompany", "Plumber", "RoofingContractor",
    "InternetCafe", "LegalService", "Attorney", "Notary", "Library", "LodgingBusiness", "BedAndBreakfast", "Campground", "Hostel", "Hotel",
    "Motel", "Resort", "MedicalBusiness", "CommunityHealth", "Dentist", "Dermatology", "DietNutrition", "Emergency", "Geriatric", "Gynecologic",
    "MedicalClinic", "Midwifery", "Nursing", "Obstetric", "Oncologic", "Optician", "Optometric", "Otolaryngologic", "Pediatric", "Pharmacy",
    "Physician", "Physiotherapy", "PlasticSurgery", "Podiatric", "PrimaryCare", "Psychiatric", "PublicHealth", "ProfessionalService",
    "RadioStation", "RealEstateAgent", "RecyclingCenter", "SelfStorage", "ShoppingCenter", "SportsActivityLocation", "BowlingAlley",
    "ExerciseGym", "GolfCourse", "HealthClub", "PublicSwimmingPool", "SkiResort", "SportsClub", "StadiumOrArena", "TennisComplex", "Store",
    "AutoPartsStore", "BikeStore", "BookStore", "ClothingStore", "ComputerStore", "ConvenienceStore", "DepartmentStore", "ElectronicsStore",
    "Florist", "FurnitureStore", "GardenStore", "GroceryStore", "HardwareStore", "HobbyShop", "HomeGoodsStore", "JewelryStore", "LiquorStore",
    "MensClothingStore", "MobilePhoneStore", "MovieRentalStore", "MusicStore", "OfficeEquipmentStore", "OutletStore", "PawnShop", "PetStore",
    "ShoeStore", "SportingGoodsStore", "TireShop", "ToyStore", "WholesaleStore", "TelevisionStation", "TouristInformationCenter", "TravelAgency",
    "MedicalOrganization", "Dentist", "DiagnosticLab", "Hospital", "MedicalClinic", "Pharmacy", "Physician", "VeterinaryCare", "NGO",
    "PerformingGroup", "DanceGroup", "MusicGroup", "TheaterGroup", "SportsOrganization", "SportsTeam", "WorkersUnion", "Person", "Patient",
    "Place", "Accommodation", "Apartment", "CampingPitch", "House", "SingleFamilyResidence", "Room", "HotelRoom", "MeetingRoom", "Suite",
    "AdministrativeArea", "City", "Country", "State", "CivicStructure", "Airport", "Aquarium", "Beach", "Bridge", "BusStation", "BusStop",
    "Campground", "Cemetery", "Crematorium", "EventVenue", "FireStation", "GovernmentBuilding", "CityHall", "Courthouse", "DefenceEstablishment",
    "Embassy", "LegislativeBuilding", "Hospital", "MovieTheater", "Museum", "MusicVenue", "Park", "ParkingFacility", "PerformingArtsTheater",
    "PlaceOfWorship", "BuddhistTemple", "CatholicChurch", "Church", "HinduTemple", "Mosque", "Synagogue", "Playground", "PoliceStation",
    "RVPark", "StadiumOrArena", "SubwayStation", "TaxiStand", "TrainStation", "Zoo", "Landform", "BodyOfWater", "Canal", "LakeBodyOfWater",
    "OceanBodyOfWater", "Pond", "Reservoir", "RiverBodyOfWater", "SeaBodyOfWater", "Waterfall", "Continent", "Mountain", "Volcano",
    "LandmarksOrHistoricalBuildings", "LocalBusiness", "Residence", "ApartmentComplex", "GatedResidenceCommunity", "TouristAttraction",
    "Product", "IndividualProduct", "ProductModel", "SomeProducts", "Vehicle", "BusOrCoach", "Car", "Motorcycle", "MotorizedBicycle"
    };

        public static String[] Of_Action = new String [] {
    "Thing", "Action", "AchieveAction", "LoseAction", "TieAction", "WinAction", "AssessAction",
    "ChooseAction", "VoteAction", "IgnoreAction", "ReactAction", "AgreeAction", "DisagreeAction",
    "DislikeAction", "EndorseAction", "LikeAction", "WantAction", "ReviewAction", "ConsumeAction",
    "DrinkAction", "EatAction", "InstallAction", "ListenAction", "ReadAction", "UseAction", "WearAction",
    "ViewAction", "WatchAction", "ControlAction", "ActivateAction", "DeactivateAction", "ResumeAction",
    "SuspendAction", "CreateAction", "CookAction", "DrawAction", "FilmAction", "PaintAction", "PhotographAction",
    "WriteAction", "FindAction", "CheckAction", "DiscoverAction", "TrackAction", "InteractAction",
    "BefriendAction", "CommunicateAction", "AskAction", "CheckInAction", "CheckOutAction", "CommentAction",
    "InformAction", "ConfirmAction", "RsvpAction", "InviteAction", "ReplyAction", "ShareAction", "FollowAction",
    "JoinAction", "LeaveAction", "MarryAction", "RegisterAction", "SubscribeAction", "UnRegisterAction", "MoveAction",
    "ArriveAction", "DepartAction", "TravelAction", "OrganizeAction", "AllocateAction", "AcceptAction", "AssignAction",
    "AuthorizeAction", "RejectAction", "ApplyAction", "BookmarkAction", "PlanAction", "CancelAction", "ReserveAction",
    "ScheduleAction", "PlayAction", "ExerciseAction", "PerformAction", "SearchAction", "TradeAction", "BuyAction",
    "DonateAction", "OrderAction", "PayAction", "PreOrderAction", "QuoteAction", "RentAction", "SellAction", "TipAction",
    "TransferAction", "BorrowAction", "DownloadAction", "GiveAction", "LendAction", "ReceiveAction", "ReturnAction",
    "SendAction", "TakeAction", "UpdateAction", "AddAction", "InsertAction", "AppendAction", "PrependAction", "DeleteAction",
    "ReplaceAction"}; 
        public static String[] Of_CreativeWork = new String [] {
    "CreativeWork", "Article", "NewsArticle", "Report", "ScholarlyArticle", "MedicalScholarlyArticle",
    "SocialMediaPosting", "BlogPosting", "LiveBlogPosting", "DiscussionForumPosting", "TechArticle", "APIReference", "Atlas",
    "Blog", "Book", "Audiobook", "Chapter", "Clip", "MovieClip", "RadioClip", "TVClip", "VideoGameClip", "Collection", "ComicStory",
    "ComicCoverArt", "Comment", "Answer", "Conversation", "Course", "CreativeWorkSeason", "RadioSeason", "TVSeason", "CreativeWorkSeries",
    "BookSeries", "MovieSeries", "Periodical", "ComicSeries", "Newspaper", "RadioSeries", "TVSeries", "VideoGameSeries", "DataCatalog",
    "Dataset", "DataFeed", "Diet", "DigitalDocument", "NoteDigitalDocument", "PresentationDigitalDocument", "SpreadsheetDigitalDocument",
    "TextDigitalDocument", "EnumerationValueSet", "Episode", "RadioEpisode", "TVEpisode", "ExercisePlan", "Game", "VideoGame", "Map",
    "MediaObject", "AudioObject", "Audiobook", "DataDownload", "ImageObject", "Barcode", "MusicVideoObject", "VideoObject", "Message",
    "EmailMessage", "Movie", "MusicComposition", "MusicPlaylist", "MusicAlbum", "MusicRelease", "MusicRecording", "Painting", "Photograph",
    "PublicationIssue", "ComicIssue", "PublicationVolume", "Question", "Quotation", "Recipe", "Review", "ClaimReview", "Sculpture",
    "Series", "SoftwareApplication", "MobileApplication", "VideoGame", "WebApplication", "SoftwareSourceCode", "TVSeason", "TVSeries",
    "Thesis", "VisualArtwork", "CoverArt", "ComicCoverArt", "WebPage", "AboutPage", "CheckoutPage", "CollectionPage", "ImageGallery",
    "VideoGallery", "ContactPage", "ItemPage", "MedicalWebPage", "ProfilePage", "QAPage", "SearchResultsPage", "WebPageElement",
    "SiteNavigationElement", "Table", "WPAdBlock", "WPFooter", "WPHeader", "WPSideBar", "WebSite"}; 
        public static String[] Of_Event = new String [] {
    "Event", "BusinessEvent", "ChildrensEvent", "ComedyEvent", "CourseInstance", "DanceEvent", "DeliveryEvent", "EducationEvent",
    "EventSeries", "ExhibitionEvent", "Festival", "FoodEvent", "LiteraryEvent", "MusicEvent", "PublicationEvent", "BroadcastEvent",
    "OnDemandEvent", "SaleEvent", "ScreeningEvent", "SocialEvent", "SportsEvent", "TheaterEvent", "VisualArtsEvent"};
        public static String[] Of_Intangible = new String [] {
    "Intangible", "AlignmentObject", "Audience", "BusinessAudience", "EducationalAudience", "MedicalAudience", "Patient", "PeopleAudience",
    "MedicalAudience", "ParentAudience", "BedDetails", "Brand", "BroadcastChannel", "RadioChannel", "TelevisionChannel", "BroadcastFrequencySpecification",
    "BusTrip", "Class", "ComputerLanguage", "DataFeedItem", "Demand", "DigitalDocumentPermission", "EntryPoint", "Enumeration", "ActionStatusType", 
    "BoardingPolicyType", "BookFormatType", "BusinessEntityType", "BusinessFunction", "ContactPointOption", "DayOfWeek", "DeliveryMethod",
    "LockerDelivery", "ParcelService", "DigitalDocumentPermissionType", "EnumerationValue", "EventStatusType", "GamePlayMode",
    "GameServerStatus", "GenderType", "ItemAvailability", "ItemListOrderType", "MapCategoryType", "MedicalEnumeration", "DrugClass",
    "DrugCost", "DrugCostCategory", "DrugPregnancyCategory", "DrugPrescriptionStatus", "InfectiousAgentClass", "MedicalAudience",
    "MedicalDevicePurpose", "MedicalEvidenceLevel", "MedicalImagingTechnique", "MedicalObservationalStudyDesign", "MedicalProcedureType",
    "MedicalSpecialty", "MedicalStudyStatus", "MedicalTrialDesign", "MedicineSystem", "PhysicalExam", "MedicalTrialDesign",
    "MusicAlbumProductionType", "MusicAlbumReleaseType", "MusicReleaseFormatType", "OfferItemCondition", "OrderStatus", "PaymentMethod",
    "PaymentCard", "CreditCard", "PaymentStatusType", "PhysicalActivityCategory", "QualitativeValue", "BedType", "CarUsageType",
    "DriveWheelConfigurationValue", "SteeringPositionValue", "ReservationStatusType", "RestrictedDiet", "RsvpResponseType", "Specialty",
    "MedicalSpecialty", "WarrantyScope", "Flight", "GameServer", "HealthInsurancePlan", "HealthPlanCostSharingSpecification",
    "HealthPlanFormulary", "HealthPlanNetwork", "Invoice", "ItemList", "BreadcrumbList", "OfferCatalog", "JobPosting", "Language",
    "ListItem", "Offer", "AggregateOffer", "Order", "OrderItem", "ParcelDelivery", "Permit", "GovernmentPermit", "ProgramMembership",
    "Property", "PropertyValueSpecification", "Quantity", "Distance", "Duration", "Energy", "Mass", "Rating", "AggregateRating",
    "Reservation", "BusReservation", "EventReservation", "FlightReservation", "FoodEstablishmentReservation", "LodgingReservation",
    "RentalCarReservation", "ReservationPackage", "TaxiReservation", "TrainReservation", "Role", "LinkRole", "OrganizationRole",
    "EmployeeRole", "PerformanceRole", "Seat", "Service", "BroadcastService", "CableOrSatelliteService", "FinancialProduct", "BankAccount",
    "DepositAccount", "CurrencyConversionService", "InvestmentOrDeposit", "DepositAccount", "LoanOrCredit", "CreditCard", "PaymentCard",
    "PaymentService", "FoodService", "GovernmentService", "TaxiService", "ServiceChannel", "StructuredValue", "ContactPoint", "PostalAddress",
    "EngineSpecification", "GeoCoordinates", "GeoShape", "GeoCircle", "InteractionCounter", "MonetaryAmount", "NutritionInformation",
    "OpeningHoursSpecification", "OwnershipInfo", "PriceSpecification", "CompoundPriceSpecification", "DeliveryChargeSpecification",
    "PaymentChargeSpecification", "UnitPriceSpecification", "PropertyValue", "LocationFeatureSpecification", "QuantitativeValue",
    "TypeAndQuantityNode", "WarrantyPromise", "Ticket", "TrainTrip"};
        public static String[] Of_MedEntity = new String [] {
    "MedicalEntity", "AnatomicalStructure", "Bone", "BrainStructure",
    "Joint", "Ligament", "Muscle", "Nerve", "Vessel", "Artery", "LymphaticVessel", "Vein", "AnatomicalSystem", "LifestyleModification",
    "Diet", "PhysicalActivity", "ExercisePlan", "MedicalCause", "MedicalCondition", "InfectiousDisease", "MedicalSignOrSymptom",
    "MedicalSign", "VitalSign", "MedicalSymptom", "MedicalContraindication", "MedicalDevice", "MedicalGuideline", "MedicalGuidelineContraindication",
    "MedicalGuidelineRecommendation", "MedicalIndication", "ApprovedIndication", "PreventionIndication", "TreatmentIndication",
    "MedicalIntangible", "DDxElement", "DoseSchedule", "MaximumDoseSchedule", "RecommendedDoseSchedule", "ReportedDoseSchedule",
    "DrugLegalStatus", "DrugStrength", "MedicalCode", "MedicalConditionStage", "MedicalProcedure", "DiagnosticProcedure", 
    "PalliativeProcedure", "PhysicalExam", "SurgicalProcedure", "TherapeuticProcedure", "MedicalTherapy", "OccupationalTherapy", "PalliativeProcedure",
    "PhysicalTherapy", "RadiationTherapy", "RespiratoryTherapy", "PsychologicalTreatment", "MedicalRiskEstimator", "MedicalRiskCalculator",
    "MedicalRiskScore", "MedicalRiskFactor", "MedicalStudy", "MedicalObservationalStudy", "MedicalTrial", "MedicalTest", "BloodTest", "ImagingTest",
    "MedicalTestPanel", "PathologyTest", "Substance", "DietarySupplement", "Drug", "SuperficialAnatomy"};
        public static String[] Of_OrgPerson = new String [] {
    "Organization", "Airline", "Corporation", "EducationalOrganization", "CollegeOrUniversity", "ElementarySchool", "HighSchool", "MiddleSchool", "Preschool",
    "School", "GovernmentOrganization", "LocalBusiness", "AnimalShelter", "AutomotiveBusiness", "AutoBodyShop", "AutoDealer", "AutoPartsStore",
    "AutoRental", "AutoRepair", "AutoWash", "GasStation", "MotorcycleDealer", "MotorcycleRepair", "ChildCare", "Dentist", "DryCleaningOrLaundry",
    "EmergencyService", "FireStation", "Hospital", "PoliceStation", "EmploymentAgency", "EntertainmentBusiness", "AdultEntertainment",
    "AmusementPark", "ArtGallery", "Casino", "ComedyClub", "MovieTheater", "NightClub", "FinancialService", "AccountingService",
    "AutomatedTeller", "BankOrCreditUnion", "InsuranceAgency", "FoodEstablishment", "Bakery", "BarOrPub", "Brewery",
    "CafeOrCoffeeShop", "Distillery", "FastFoodRestaurant", "IceCreamShop", "Restaurant", "Winery", "GovernmentOffice", "PostOffice",
    "HealthAndBeautyBusiness", "BeautySalon", "DaySpa", "HairSalon", "HealthClub", "NailSalon", "TattooParlor", "HomeAndConstructionBusiness",
    "Electrician", "GeneralContractor", "HVACBusiness", "HousePainter", "Locksmith", "MovingCompany", "Plumber", "RoofingContractor",
    "InternetCafe", "LegalService", "Attorney", "Notary", "Library", "LodgingBusiness", "BedAndBreakfast", "Campground", "Hostel", "Hotel",
    "Motel", "Resort", "MedicalBusiness", "CommunityHealth", "Dentist", "Dermatology", "DietNutrition", "Emergency", "Geriatric", "Gynecologic",
    "MedicalClinic", "Midwifery", "Nursing", "Obstetric", "Oncologic", "Optician", "Optometric", "Otolaryngologic", "Pediatric", "Pharmacy",
    "Physician", "Physiotherapy", "PlasticSurgery", "Podiatric", "PrimaryCare", "Psychiatric", "PublicHealth", "ProfessionalService",
    "RadioStation", "RealEstateAgent", "RecyclingCenter", "SelfStorage", "ShoppingCenter", "SportsActivityLocation", "BowlingAlley",
    "ExerciseGym", "GolfCourse", "HealthClub", "PublicSwimmingPool", "SkiResort", "SportsClub", "StadiumOrArena", "TennisComplex", "Store",
    "AutoPartsStore", "BikeStore", "BookStore", "ClothingStore", "ComputerStore", "ConvenienceStore", "DepartmentStore", "ElectronicsStore",
    "Florist", "FurnitureStore", "GardenStore", "GroceryStore", "HardwareStore", "HobbyShop", "HomeGoodsStore", "JewelryStore", "LiquorStore",
    "MensClothingStore", "MobilePhoneStore", "MovieRentalStore", "MusicStore", "OfficeEquipmentStore", "OutletStore", "PawnShop", "PetStore",
    "ShoeStore", "SportingGoodsStore", "TireShop", "ToyStore", "WholesaleStore", "TelevisionStation", "TouristInformationCenter", "TravelAgency",
    "MedicalOrganization", "Dentist", "DiagnosticLab", "Hospital", "MedicalClinic", "Pharmacy", "Physician", "VeterinaryCare", "NGO",
    "PerformingGroup", "DanceGroup", "MusicGroup", "TheaterGroup", "SportsOrganization", "SportsTeam", "WorkersUnion", "Person", "Patient"};
        public static String[] Of_PlaceProduct = new String [] {
    "Place", "Accommodation", "Apartment", "CampingPitch", "House", "SingleFamilyResidence", "Room", "HotelRoom", "MeetingRoom", "Suite",
    "AdministrativeArea", "City", "Country", "State", "CivicStructure", "Airport", "Aquarium", "Beach", "Bridge", "BusStation", "BusStop",
    "Campground", "Cemetery", "Crematorium", "EventVenue", "FireStation", "GovernmentBuilding", "CityHall", "Courthouse", "DefenceEstablishment",
    "Embassy", "LegislativeBuilding", "Hospital", "MovieTheater", "Museum", "MusicVenue", "Park", "ParkingFacility", "PerformingArtsTheater",
    "PlaceOfWorship", "BuddhistTemple", "CatholicChurch", "Church", "HinduTemple", "Mosque", "Synagogue", "Playground", "PoliceStation",
    "RVPark", "StadiumOrArena", "SubwayStation", "TaxiStand", "TrainStation", "Zoo", "Landform", "BodyOfWater", "Canal", "LakeBodyOfWater",
    "OceanBodyOfWater", "Pond", "Reservoir", "RiverBodyOfWater", "SeaBodyOfWater", "Waterfall", "Continent", "Mountain", "Volcano",
    "LandmarksOrHistoricalBuildings", "LocalBusiness", "Residence", "ApartmentComplex", "GatedResidenceCommunity", "TouristAttraction",
    "Product", "IndividualProduct", "ProductModel", "SomeProducts", "Vehicle", "BusOrCoach", "Car", "Motorcycle", "MotorizedBicycle"
    };


    
    public static String[][] dataRelations = new String[21000][21000];
    public static ArrayList<Tabl> ttbbll = new ArrayList<Tabl>();
    Tabl tbl = new Tabl(null);
    
    public static final JCheckBox fileChoosercBox = new JCheckBox(" enable / disable");

    public static String pathDField = "D:\\Freelancer\\MPage\\ODictionary\\";
    public static String pathTField = "D:\\Freelancer\\MPage\\ODictionary\\FilesXML\\";
    public static String[] rowTemplate = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    public static final JCheckBox showMessBox = new JCheckBox(" enable / disable");
    public static JTextField qtableField = new JTextField("20", JButton.RIGHT);

    public ArrayList<String> pList = new ArrayList<String>(); 
    public ArrayList<String> fList = new ArrayList<String>(); 
    public ArrayList<String> cList = new ArrayList<String>(); 

    public ArrayList<String> tList = new ArrayList<String>(); 
    public ArrayList<String> vList = new ArrayList<String>(); 
    public ArrayList<String> eList = new ArrayList<String>(); 
    public ArrayList<String> hList = new ArrayList<String>(); 



    public static javax.swing.JTabbedPane jToolTabPanel = new JTabbedPane();
    private int qtb, qtbThing;
    private String fileTName;

    public Tabl mt = null;
    public Tabl thr = null;
    private static final JPanel hierarchyPanel = new JPanel();
    private static final JPanel dictionaryPanel = new JPanel();

    private javax.swing.JScrollPane jScrollThingTables = new javax.swing.JScrollPane();
    private javax.swing.JScrollPane jScrollTables = new javax.swing.JScrollPane();
    public static javax.swing.JTabbedPane tabls = new javax.swing.JTabbedPane();
    public static javax.swing.JTabbedPane things = new javax.swing.JTabbedPane();
    
    private final  javax.swing.JButton graph1Graph = new JButton("Graph1 XML");
    private final  javax.swing.JButton graph2Graph = new JButton("Graph2 XML");
    private final  javax.swing.JButton graph3Graph = new JButton("Graph3 XML");
    private final  javax.swing.JButton tablRelations = new JButton("Relations");

    private final  javax.swing.JButton graphType = new JButton("GraphOf Types");
    private final  javax.swing.JButton graph_2Graph = new JButton("GraphBy_Fields");
    private final  javax.swing.JButton graph_3Graph = new JButton("Graph_3 XML");
    private final  javax.swing.JButton graph_4Graph = new JButton("Property by Types");
    private final  javax.swing.JButton showRelations = new JButton("toShow Relations");

    public ToolBar(){
        
        super("MarkUp ToolBar, 2016");
    
        getContentPane().add(jToolTabPanel);

        JMenu helpMenu = new JMenu("Help");
            helpMenu.add(new ToolBar.HelpAction());
            helpMenu.addSeparator();
            helpMenu.setMnemonic('H');

        JMenuBar menuBar = new JMenuBar();
            menuBar.add(helpMenu);
            setJMenuBar(menuBar);

            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            
        initComponents();
    }
    
    
    public static void main(String args[]) {
        ToolBar tb = new ToolBar();
        tb.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tb.setVisible(true);
    }

    private class HelpAction extends AbstractAction {
        public HelpAction() {
            Icon ic = UIManager.getIcon("OptionPane.questionIcon");
            putValue(Action.SMALL_ICON, ic);
            putValue(Action.NAME, "Useful information");
            putValue(Action.SHORT_DESCRIPTION, "Help for working with ontology");
            putValue(Action.MNEMONIC_KEY, new Integer('N'));
        }
    
        public void actionPerformed(ActionEvent event) {
            //
        }
    }
                 
    
    public void initComponents() {
   
        showRelations.addActionListener((ActionEvent e) -> {
            qtbThing = jTableThingPlenty.getRowCount() ;
            getThingRelations();
        });
        tablRelations.addActionListener((ActionEvent e) -> {
            qtb = jTablePlenty.getRowCount() ;
            getAllTables(tabls);
        });
        graph3Graph.addActionListener((ActionEvent e) -> {
            //getIntentionGraphXML();
        });
        graph2Graph.addActionListener((ActionEvent e) -> {
            //getGrammarGraphXML();
        });
        graph_2Graph.addActionListener((ActionEvent e) -> {
            getByFieldsGraphXML();
        });
        graph_3Graph.addActionListener((ActionEvent e) -> {
//            getGraphByFields();
            getTypeFields(Of_Action, "Of_Action");
            getTypeFields(Of_CreativeWork, "Of_CreativeWork");
            getTypeFields(Of_Event, "Of_Event");
            getTypeFields(Of_Intangible, "Of_Intangible");
            getTypeFields(Of_MedEntity, "Of_MedEntity");
            getTypeFields(Of_OrgPerson, "Of_OrgPerson");
            getTypeFields(Of_PlaceProduct, "Of_PlaceProduct");
        });
        graph_4Graph.addActionListener((ActionEvent e) -> {
//            try {
//                doCreatePType();
//            } catch (IOException ex) {
//                Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
//            }
                            
                            doFillProperty();

        });
        graph1Graph.addActionListener((ActionEvent e) -> {
            new ParseTypeTree();
        });    
        graphType.addActionListener((ActionEvent e) -> {
            getHierarchyTypeXML();
        });    
        
    // Dictionary panel for Scheme.org types
    //==========================================================================
        DictionaryThing dt = new DictionaryThing();
        dt.getThingDictionaryPane(hierarchyPanel).getAccessibleContext().setAccessibleParent(jToolTabPanel);
        jToolTabPanel.getAccessibleContext().setAccessibleName("Types");
        jToolTabPanel.addTab("Types", dt.getThingDictionaryPane(hierarchyPanel));
        jToolTabPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    // The presentation panel
    //==========================================================================
        jScrollThingTables.setViewportView(getThingInterface());
        jScrollThingTables.getAccessibleContext().setAccessibleParent(jToolTabPanel);
        jToolTabPanel.getAccessibleContext().setAccessibleName("Properties");
        jToolTabPanel.addTab("Properties", jScrollThingTables);
        jToolTabPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    // Dictionary panel for webpages
    //==========================================================================
        DictionaryPane dp = new DictionaryPane();
        dp.getDictionaryPane(dictionaryPanel).getAccessibleContext().setAccessibleParent(jToolTabPanel);
        jToolTabPanel.getAccessibleContext().setAccessibleName("Sites");
        jToolTabPanel.addTab("Sites", dp.getDictionaryPane(dictionaryPanel));
        jToolTabPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    // The presentation panel
    //==========================================================================
        jScrollTables.setViewportView(getBut(tabls));
        jScrollTables.getAccessibleContext().setAccessibleParent(jToolTabPanel);
        jToolTabPanel.getAccessibleContext().setAccessibleName("DataConnection");
        jToolTabPanel.addTab("DataConnection", jScrollTables);
        jToolTabPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
 
    public void getAllTables(javax.swing.JTabbedPane gat){
        String tablname = ""; 
       
        if(mt==null ){
            for(int i=0; i<qtb; i++) {
                mt = new Tabl(gat);
                mt.getTableTemplate(null).getAccessibleContext().setAccessibleParent(gat);
                gat.getAccessibleContext().setAccessibleName("Table "+i);
                tablname = DictionaryPane.jTablePlenty.getValueAt(i, 1).toString();
                gat.addTab("Table "+i, mt.getTableTemplate(tablname));
            }
        } else {
            for(int i=qtb+1; i<Integer.parseInt(qtableField.getText()); i++) {
                mt = new Tabl(gat);
                mt.getTableTemplate(null).getAccessibleContext().setAccessibleParent(gat);
                gat.getAccessibleContext().setAccessibleName("Table "+i);
                tablname = DictionaryPane.jTablePlenty.getValueAt(i, 1).toString();
                gat.addTab("Table "+i, mt.getTableTemplate(tablname));
//                ttbbll.add(mt);
            }
            qtb  = Integer.parseInt(qtableField.getText());
        }
    }

/*
    public void getAllTables(javax.swing.JTabbedPane gat){
        String tablname = ""; 
       
        if(mt==null ){
            for(int i=0; i<qtb; i++) {
                mt = new Tabl(gat);
                mt.getTableTemplate(null).getAccessibleContext().setAccessibleParent(gat);
                gat.getAccessibleContext().setAccessibleName("Table "+i);
                tablname = DictionaryPane.jTablePlenty.getValueAt(i, 1).toString();
                gat.addTab("Table "+i, mt.getTableTemplate(tablname));
            }
        } else {
            for(int i=qtb+1; i<Integer.parseInt(qtableField.getText()); i++) {
                mt = new Tabl(gat);
                mt.getTableTemplate(null).getAccessibleContext().setAccessibleParent(gat);
                gat.getAccessibleContext().setAccessibleName("Table "+i);
                tablname = DictionaryPane.jTablePlenty.getValueAt(i, 1).toString();
                gat.addTab("Table "+i, mt.getTableTemplate(tablname));
            }
            qtb  = Integer.parseInt(qtableField.getText());
        }
    }
   
    
    */    
    public JPanel getBut(javax.swing.JTabbedPane tbp){
        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(javax.swing.BorderFactory.createTitledBorder("The presentations of the type of relations"));
        buttonPane.setBackground(Color.GRAY);
   
        GroupLayout layout = new GroupLayout(buttonPane);
        buttonPane.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(graph1Graph, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(graph2Graph, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(graph3Graph, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tablRelations, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
                    
                        .addComponent(tbp, GroupLayout.PREFERRED_SIZE, 1500, GroupLayout.PREFERRED_SIZE))
            );
		
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(graph1Graph, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(graph2Graph, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(graph3Graph, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tablRelations, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                      .addComponent(tbp, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
            )
        );
        return buttonPane;
   }

    // To create the tables for presentation the semantic relations
    //==========================================================================
    public void getThingRelations(){
        String rname = ""; 
        ttbbll = new ArrayList<Tabl>();

        if(thr==null ){
            for(int i=0; i<qtbThing; i++) {
                thr = new Tabl(things);
                thr.getTableTemplate(null).getAccessibleContext().setAccessibleParent(things);
                rname = jTableThingPlenty.getModel().getValueAt(i, 1).toString();
                things.getAccessibleContext().setAccessibleName(rname);
                things.addTab(rname, thr.getTableTemplate(rname));
                ttbbll.add(thr);
            }
        } else {
            for(int i=qtbThing+1; i<jTableThingPlenty.getRowCount(); i++) {
                thr = new Tabl(things);
                thr.getTableTemplate(null).getAccessibleContext().setAccessibleParent(things);
                rname = jTableThingPlenty.getModel().getValueAt(i, 1).toString();
                things.getAccessibleContext().setAccessibleName(jTableThingPlenty.getModel().getValueAt(i, 1).toString());
                things.addTab(rname, thr.getTableTemplate(rname));
                ttbbll.add(thr);
            }
            qtbThing  = jTableThingPlenty.getRowCount();
        }
    }
   
    // To create interface part for relation presentation
    //==========================================================================
    public JPanel getThingInterface(){
        JPanel buttonThingPane = new JPanel();
        buttonThingPane.setBorder(javax.swing.BorderFactory.createTitledBorder("The presentations of the type's relations regards to Scheme.org"));
        buttonThingPane.setBackground(Color.lightGray);
   
        GroupLayout layout = new GroupLayout(buttonThingPane);
        buttonThingPane.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(graphType, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(graph_2Graph, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(graph_3Graph, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(graph_4Graph, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(showRelations, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                    
                        .addComponent(things, GroupLayout.PREFERRED_SIZE, 1500, GroupLayout.PREFERRED_SIZE))
            );
		
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(graphType, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(graph_2Graph, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(graph_3Graph, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(graph_4Graph, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(showRelations, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                      .addComponent(things, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
            )
        );
        return buttonThingPane;
   }

    // To search the type of relations
    //==========================================================================
    private int getPosition(String strPos){
        int pos = -1;
        for(int i=0; i<DictionaryPane.jTablePlenty.getRowCount(); i++)
            if(DictionaryPane.jTablePlenty.getModel().getValueAt(i, 1).toString().equals(strPos)) {pos = i;}
        if (pos==-1) {JOptionPane.showMessageDialog(null, "Incorrect name of relations ?");}
        return pos;
    }
    
    // To search the type of relations for Thing
    //==========================================================================
    private int getTypePosition(String strPos){
        int pos = -1;
        for(int i=0; i<jTableThingPlenty.getRowCount(); i++)
            if(jTableThingPlenty.getModel().getValueAt(i, 1).toString().equals(strPos)) {pos = i;}
        if (pos==-1) {JOptionPane.showMessageDialog(null, "Incorrect name of relation !?");}
        return pos;
    }
    
    // To make path from String
    //==========================================================================
    private String[] getPath(String strPath){
        String[] path = null;
        int pos = getTypePosition("isEntity");
        int pos_ot1 = 1000*pos;   int pos_ot1_end = 1000*pos+1000;
        for (int i_ot1=pos_ot1+1; i_ot1<pos_ot1_end; i_ot1++) {
            if (strPath.equals(dataRelations[i_ot1][pos_ot1])) {
                for (int j_ot1=pos_ot1+1; j_ot1<pos_ot1_end; j_ot1++) {
                    if (dataRelations[i_ot1][j_ot1]==null) {continue;}
                    if (dataRelations[i_ot1][j_ot1].equals("X")) {
                        String path_value = dataRelations[pos_ot1][j_ot1]; 
                        String tpath = path_value.substring(1, path_value.length()-1);
                        path = tpath.split(" : ");
                        for (int ii=0; ii<path.length; ii++)
                            System.out.println(path[ii]);
                    }
                }    
            }
        }    
        return path;
    }
    
    // To make path from String
    //==========================================================================
    private String[] get_to_path(String strPath){
        String[] path = null;
        String tpath = strPath.substring(1, strPath.length()-1);
        path = tpath.split(" : ");
        for (int ii=0; ii<path.length; ii++)
            System.out.print(path[ii]+"\t");
            System.out.println("\n\r");
        return path;
    }
    
    // To find all property to the type
    //==========================================================================
    private void getProperty(ArrayList tlist, String property){
//        pList.clear();
        int pst = getTypePosition("isTypeProperty");
        int pst_start = 1000*pst;   int pst_end = 1000*pst+1000;
        for (int i_pst=pst_start+1; i_pst<pst_end; i_pst++) {
            if (property.equals(dataRelations[i_pst][pst_start])) {
                for (int j_pst=pst_start+1; j_pst<pst_end; j_pst++) {
                    if (dataRelations[i_pst][j_pst]==null) {continue;}
                    if (dataRelations[i_pst][j_pst].equals("X")) {
                        tlist.add(dataRelations[pst_start][j_pst]); 
                    }
                }
            }
        }
//        return tlist;
    }
    
    
        // Search object by name
    //==========================================================================
    private ArrayList fObject(String objS, boolean os){
        ArrayList<String> entList = new ArrayList<String>();
        int pst = getTypePosition("isTypeProperty");
        int pst_start = 1000*pst;   int pst_end = 1000*pst+1000;
        for (int i_pst=pst_start+1; i_pst<pst_end; i_pst++) {
            if (objS.equals(dataRelations[i_pst][pst_start])) {
                for (int j_pst=pst_start+1; j_pst<pst_end; j_pst++) {
                    if (dataRelations[i_pst][j_pst]==null) {continue;}
                    if (dataRelations[i_pst][j_pst].equals("X")) {
                        entList.add(dataRelations[pst_start][j_pst]); 
                    }
                }
            }
        }
        return entList;
    }
    
    // Search subject by name
    //==========================================================================
    private int fSubject(String ps){
        return -1;
    }
    
    // To fill relations between properties and entities
    //==========================================================================
    private void doFillProperty(){
        String entname = null;
        ArrayList<String> entList = new ArrayList<String>();
        int p = getTypePosition("isProperty");
        things.setSelectedIndex(p);
//        String tlname = ttbbll.get(1).table.getColumnModel().getColumn(0).getHeaderValue().toString();
//        JOptionPane.showMessageDialog(null, tlname);
        int row = ttbbll.get(p).table.getRowCount();
        int col = ttbbll.get(p).table.getColumnCount();
        
        new ParseTypeTree();
//        typeArray

        for (int i=0; i<row; i++) {
            String str = ttbbll.get(p).table.getModel().getValueAt(i, 0).toString();
            ttbbll.get(p).table.setRowSelectionInterval(i, i);
            entList = new ArrayList<String>();
            entname = null;
            for (int j=0; j<typeArray.length; j++) {
                if (typeArray[j][1]==null) {continue;}
                if (typeArray[j][0]!=null) {entname = typeArray[j][0];}
                if (typeArray[j][1].equals(str)) {
                    entList.add(entname);
                    entname = null;
                }
            }
            for(int j=0; j<col; j++){
                String entity_name = ttbbll.get(p).table.getColumnModel().getColumn(j).getHeaderValue().toString();
                if(entList.contains(entity_name)) {
                    ttbbll.get(p).table.getModel().setValueAt("X", i, j);
                }
            }
        }
    }
    
    // To find all datatypes to the property
    //==========================================================================
    private void getField(ArrayList avl, String fld){
//        fList.clear();
        int pst = getTypePosition("isDataType");
        int pst_start = 1000*pst;   int pst_end = 1000*pst+1000;
        for (int i_pst=pst_start+1; i_pst<pst_end; i_pst++) {
            if (fld.equals(dataRelations[i_pst][pst_start])) {
                for (int j_pst=pst_start+1; j_pst<pst_end; j_pst++) {
                    if (dataRelations[i_pst][j_pst]==null) {continue;}
                    if (dataRelations[i_pst][j_pst].equals("X")) {
                        avl.add(dataRelations[pst_start][j_pst]); 
                    }
                }
            }
        }
//        return avl;
    }
    
    
    // To find all datatypes to the property
    //==========================================================================
    private void getFieldE(ArrayList acvl, String fe){
//        cList.clear();
        int pst = getTypePosition("isPropertyType");
        int pst_start = 1000*pst;   int pst_end = 1000*pst+1000;
        for (int i_pst=pst_start+1; i_pst<pst_end; i_pst++) {
            if (fe.equals(dataRelations[i_pst][pst_start])) {
                for (int j_pst=pst_start+1; j_pst<pst_end; j_pst++) {
                    if (dataRelations[i_pst][j_pst]==null) {continue;}
                    if (dataRelations[i_pst][j_pst].equals("X")) {
                        acvl.add(dataRelations[pst_start][j_pst]); 
                    }
                }
            }
        }
//        return cList;
    }
    
    // To find all datatypes to the property
    //==========================================================================
    private void getInheritance(ArrayList iher, String it){
        int pst = getTypePosition("isInheritanceOf");
        int pst_start = 1000*pst;   int pst_end = 1000*pst+1000;
        for (int i_pst=pst_start+1; i_pst<pst_end; i_pst++) {
            if (it.equals(dataRelations[i_pst][pst_start])) {
                for (int j_pst=pst_start+1; j_pst<pst_end; j_pst++) {
                    if (dataRelations[i_pst][j_pst]==null) {continue;}
                    if (dataRelations[i_pst][j_pst].equals("X")) {
                        iher.add(dataRelations[pst_start][j_pst]); 
                    }
                }
            }
        }
    }
    
    // To find all datatypes to the property
    //==========================================================================
    private String getPathE(String typeE){
        String ePath = "";
        int pst = getTypePosition("isEntity");
        int pst_start = 1000*pst;   int pst_end = 1000*pst+1000;
        for (int i_pst=pst_start+1; i_pst<pst_end; i_pst++) {
            if (typeE.equals(dataRelations[i_pst][pst_start])) {
                for (int j_pst=pst_start+1; j_pst<pst_end; j_pst++) {
                    if (dataRelations[i_pst][j_pst]==null) {continue;}
                    if (dataRelations[i_pst][j_pst].equals("X")) {
                        ePath = dataRelations[pst_start][j_pst]; 
                    }
                }
            }
        }
        return ePath;
    }
    
    public int ik = 0;
    public void typeMap(ArrayList constList, String typeStr, int n){
        String typePath = getPathE(typeStr);
        if (constList.indexOf(typeStr)!= -1) {return;}
        if (constList.indexOf(typeStr)== -1) {constList.add(typeStr);}
        ik +=1;
        System.out.println("\t\t ik: "+ik+"\t constList\n"+constList.toString()+"\n");
        boolean exit_prp = true;
        while (exit_prp){
            ArrayList<String> prp = new ArrayList<String>();
            getProperty(prp, typeStr);
            for(int i1=0; i1<prp.size(); i1++){
                if (pList.indexOf(prp.get(i1))== -1) {pList.add(prp.get(i1));}
                boolean exit_field = true;
                while (exit_field){
                    ArrayList<String> field = new ArrayList<String>();
                    getField(field, prp.get(i1));
                    for(int i2=0; i2<field.size(); i2++){
                        if (vList.indexOf(field.get(i2))== -1) {vList.add(field.get(i2));}
                            System.out.println("\t\t"+typePath+"\t:"+prp.get(i1)+":\t"+field.get(i2));
                        }

                    ArrayList<String> fieldE = new ArrayList<String>();
                    getFieldE(fieldE, prp.get(i1));
                    for(int i2=0; i2<fieldE.size(); i2++){
                        if (eList.indexOf(fieldE.get(i2))== -1) {eList.add(fieldE.get(i2));}
                        System.out.println("\t\t"+typePath+"\t:"+prp.get(i1)+":\t"+fieldE.get(i2)); 
                        typeMap(constList, fieldE.get(i2), n+1);
//                                System.out.println("eList.size"+eList.size());
//                                eList.remove(eList.size()-1);
//                                System.out.println("eList.size"+eList.size());
                    }
                    exit_field = false;
                }
            }
            
            boolean exit_inh = true;
            while (exit_inh){
                ArrayList<String> inheritList = new ArrayList<String>();
                getInheritance(inheritList, typeStr);
                for(int i2=0; i2<inheritList.size(); i2++){
                    if (hList.indexOf(inheritList.get(i2))== -1) {hList.add(inheritList.get(i2));}
                    System.out.println("\t\t"+typePath+"\t:"+typeStr+":\t"+inheritList.get(i2)); 
                    typeMap(constList, inheritList.get(i2), n+1);
                }
                exit_inh = false;
            }        
                    exit_prp = false;
                }
                
                

    }


    //To delete duplication and to sort after the list of array
    //===========================================
    public ArrayList<String> DelDupl(ArrayList<String> array){
        ArrayList<String> result = new ArrayList<String>(new HashSet<String>(array));
        Collections.sort(result);
//        System.out.println(result);
        return result;
    }
    
    
    //************************************************************************************
    
    
    // To create JSONArray from data
    //===========================================
    public void doCreatePType() throws IOException {
//        new ParseTypeTree();
        int pos = 0;
        pos = getTypePosition("isDataType");
        int pos_ot4 = 1000*pos;   int pos_ot4_end = 1000*pos+1000;

	JSONObject obj = new JSONObject();
	JSONObject objE = new JSONObject();
	JSONObject objP = new JSONObject();
            JSONArray eList = new JSONArray(); 
            JSONArray pList = new JSONArray(); 
            JSONArray dList = new JSONArray(); 
        String ent_name = "";    
        String prp_name = "";

        int i = 0;
        boolean stPrp = false;
        boolean stEnt = false;
        while(typeArray[i][2]!=null){
            if ((typeArray[i][0]!=null) & (typeArray[i][1]!=null) & (typeArray[i][2]!=null)) {
                if(stEnt){
                    objE = new JSONObject();
                    objE.put(ent_name, pList);
                    eList.add(objE);
                    obj.put("Entity", eList);
                    stEnt = false;
                }
                stEnt = true;
                ent_name=typeArray[i][0];
                prp_name=typeArray[i][1];
                pList = new JSONArray();
                dList = new JSONArray();
                dList.add(typeArray[i++][2]);
                objP = new JSONObject();
                stPrp = true;
            }    
            while ((typeArray[i][0]==null) & (typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                while ((typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                    dList.add(typeArray[i++][2]);
                }
                objP.put(prp_name, dList);
                pList.add(objP);
            }    

            while ((typeArray[i][0]==null) & (typeArray[i][1]!=null) & (typeArray[i][2]!=null)) {
                if (stPrp) {
                    objP.put(prp_name, dList);
                    pList.add(objP);
                    stPrp = false;
                }
                prp_name = typeArray[i][1];
                dList = new JSONArray();
                dList.add(typeArray[i++][2]);
                while ((typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                    dList.add(typeArray[i++][2]);
                }
                objP = new JSONObject();
                objP.put(prp_name, dList);
                pList.add(objP);
                dList = new JSONArray();
            }    
        }     
            
	try {
		FileWriter file = new FileWriter(FileManager.main("To choose JSON file for writing!"));
		file.write(obj.toJSONString());
		file.flush();
		file.close();
	} catch (IOException e) {
		e.printStackTrace();
	}

//	System.out.print(obj);
    }

    
    
    
    
    
        // To create the answer graph
    // =========================================================================
    public void getTypeFields(String[] nameGroup, String nOf){
        int pos = 0;
        String fn = "isFields_"+nOf+".xml";
        boolean flag = false;
        String curProperty = "";
//        vList = new ArrayList<String>();
//        pList = new ArrayList<String>();
//        eList = new ArrayList<String>();
//        typeMap(tList, "Action", 0);
//        System.out.println(pList.toString()+"\n");
//        System.out.println(vList.toString()+"\n");
//        System.out.println(eList.toString()+"\n");
//        System.out.println(tList.toString());
//        if (pos==0) {return;}
        fileTName = pathTField+fn;
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("dictionary files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setDialogTitle("Choose a file to save of the Hierarchy of types"); 
        fileChooser.setSelectedFile(new File(fileTName));
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileTName = fileChooser.getSelectedFile().getAbsolutePath();
        }    

        try {
            DocumentBuilderFactory docTFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docTBuilder = docTFactory.newDocumentBuilder();
		Document doc = docTBuilder.newDocument();
		Element rootElement = doc.createElement("types");
		doc.appendChild(rootElement);
            
            String typeBySchema = null;    
            for(int i=0; i<nameGroup.length; i++) {   
                typeBySchema = nameGroup[i]; 
                Element hierarchy = doc.createElement("is-type"); 
                    Element type_name = doc.createElement("type");
                    type_name.appendChild(doc.createTextNode(typeBySchema));
                    hierarchy.appendChild(type_name);
                            
                    tList = new ArrayList<String>();
                    vList = new ArrayList<String>();
                    pList = new ArrayList<String>();
                    eList = new ArrayList<String>();
                    typeMap(tList, typeBySchema, 0);

                    pList = DelDupl(pList);
                    for (int it=0; it<pList.size(); it++) {
                        curProperty = pList.get(it);
                        pos = getTypePosition("isDataType");
                        int pos_ot4 = 1000*pos;   int pos_ot4_end = 1000*pos+1000;
                        Element is_property = doc.createElement("is-property"); 
                        
                        for (int i_ot4=pos_ot4+1; i_ot4<pos_ot4_end; i_ot4++) {
                            if (curProperty.equals(dataRelations[i_ot4][pos_ot4])) {
                                flag = true;
                                for (int j_ot4=pos_ot4+1; j_ot4<pos_ot4_end; j_ot4++) {
                                    if (dataRelations[i_ot4][j_ot4]==null) {continue;}
                                    if (dataRelations[i_ot4][j_ot4].equals("X")) {
                                        if (flag) {
                                            Element pname = doc.createElement("pname"); 
                                            pname.appendChild(doc.createTextNode(curProperty)); 
                                            is_property.appendChild(pname);
                                            flag = false;
                                        }

                                        Element dtype = doc.createElement("dtype"); 
                                            String dtype_value = dataRelations[pos_ot4][j_ot4]; 
                                            dtype.appendChild(doc.createTextNode(dtype_value)); 
                                        is_property.appendChild(dtype);
                                                                hierarchy.appendChild(is_property);

                                    }
                                }
                            }
                        }
                    }        
                rootElement.appendChild(hierarchy);
            }        

        // write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
                
	StreamResult result = new StreamResult(new File(fileTName));
	transformer.transform(source, result);
        JOptionPane.showMessageDialog(null, "File saved!");

	} catch (ParserConfigurationException | TransformerException pce) { }
    }
    
    
    
    
    
        // To create the answer graph
    // =========================================================================
    public void getGraphByFields(){
/*            
            for(int i=0; i<8000; i++)
                for(int j=0; j<8000; j++) {
                    if (dataRelations[i][j]==null) {continue;}
                    System.out.println("i="+i+" j= "+j+"  : "+dataRelations[i][j]);
                }
*/        
        int pos = 0;
        vList = new ArrayList<String>();
        pList = new ArrayList<String>();
        vList = new ArrayList<String>();
        typeMap(tList, "Action", 0);
        System.out.println(pList.toString()+"\n");
        System.out.println(vList.toString()+"\n");
        System.out.println(eList.toString()+"\n");
        System.out.println(tList.toString());
        if (pos==0) {return;}
        fileTName = pathTField+"TypeByFields.xml";
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("dictionary files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setDialogTitle("Choose a file to save of the Hierarchy of types"); 
        fileChooser.setSelectedFile(new File(fileTName));
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileTName = fileChooser.getSelectedFile().getAbsolutePath();
        }    

        try {
            DocumentBuilderFactory docTFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docTBuilder = docTFactory.newDocumentBuilder();
		Document doc = docTBuilder.newDocument();
		Element rootElement = doc.createElement("types");
		doc.appendChild(rootElement);
            
            String schema = null;    
            for(int i=0; i<hSchema.length; i++) {   
                schema = hSchema[i]; 
                // ========   To find the "isTypeProperty" position for types =====================
                pos = getTypePosition("isTypeProperty");
                int pos_ot2 = 1000*pos;   int pos_ot2_end = 1000*pos+1000;
                Element hierarchy = doc.createElement("is-type"); 
                    Element type_name = doc.createElement("type-name");
                    type_name.appendChild(doc.createTextNode(schema));
                    hierarchy.appendChild(type_name);
                for (int i_ot2=pos_ot2+1; i_ot2<pos_ot2_end; i_ot2++) {
                    if (schema.equals(dataRelations[i_ot2][pos_ot2])) {
                        for (int j_ot2=pos_ot2+1; j_ot2<pos_ot2_end; j_ot2++) {
                            if (dataRelations[i_ot2][j_ot2]==null) {continue;}
                            if (dataRelations[i_ot2][j_ot2].equals("X")) {
                                Element is_property = doc.createElement("is-property"); 
                                    Element prp_name = doc.createElement("property"); 
                                String property_value = dataRelations[pos_ot2][j_ot2]; 
                                prp_name.appendChild(doc.createTextNode(property_value)); 
                                is_property.appendChild(prp_name);
// Start / Property / DataType
                // ========   To find the "isDataType" position for property =====================
                pos = getTypePosition("isDataType");
                int pos_ot4 = 1000*pos;   int pos_ot4_end = 1000*pos+1000;
                Element is_data_type = doc.createElement("is-dtype"); 
                for (int i_ot4=pos_ot4+1; i_ot4<pos_ot4_end; i_ot4++) {
                    if (property_value.equals(dataRelations[i_ot4][pos_ot4])) {
                        for (int j_ot4=pos_ot4+1; j_ot4<pos_ot4_end; j_ot4++) {
                            if (dataRelations[i_ot4][j_ot4]==null) {continue;}
                            if (dataRelations[i_ot4][j_ot4].equals("X")) {
                                Element is_type = doc.createElement("dtype"); 
                                String type_value = dataRelations[pos_ot4][j_ot4]; 
                                is_type.appendChild(doc.createTextNode(type_value)); 
                                is_data_type.appendChild(is_type);
                            }
                        }
                    }
                }
                is_property.appendChild(is_data_type);
                // ========   To find the "isDataType" position for property =====================
// End / Property / DataType
   
// Start / Property / PropertyType
                // ========   To find the "isPropertyType" position for property =====================
                pos = getTypePosition("isPropertyType");
                int pos_ot6 = 1000*pos;   int pos_ot6_end = 1000*pos+1000;
                Element is_data_etype = doc.createElement("is-etype"); 
                for (int i_ot6=pos_ot6+1; i_ot6<pos_ot6_end; i_ot6++) {
                    if (property_value.equals(dataRelations[i_ot6][pos_ot6])) {
                        for (int j_ot6=pos_ot6+1; j_ot6<pos_ot6_end; j_ot6++) {
                            if (dataRelations[i_ot6][j_ot6]==null) {continue;}
                            if (dataRelations[i_ot6][j_ot6].equals("X")) {
                                Element is_etype = doc.createElement("etype"); 
                                    Element etype_name = doc.createElement("type-name"); 
                                String etype_value = dataRelations[pos_ot6][j_ot6]; 
                                etype_name.appendChild(doc.createTextNode(etype_value)); 
                                is_etype.appendChild(etype_name);
// Start / Property / DataType
                // ========   To find the "isTypeProperty" position for property =====================
                pos = getTypePosition("isTypeProperty");
                int pos_ot2_1 = 1000*pos;   int pos_ot2_1_end = 1000*pos+1000;
                Element is_etype_dtype = doc.createElement("types"); 
                for (int i_ot2_1=pos_ot2_1+1; i_ot2_1<pos_ot2_1_end; i_ot2_1++) {
                    if (etype_value.equals(dataRelations[i_ot2_1][pos_ot2_1])) {
                        for (int j_ot2_1=pos_ot2_1+1; j_ot2_1<pos_ot2_1_end; j_ot2_1++) {
                            if (dataRelations[i_ot2_1][j_ot2_1]==null) {continue;}
                            if (dataRelations[i_ot2_1][j_ot2_1].equals("X")) {
                                Element is_type = doc.createElement("is-type"); 
                                    Element is_e_type = doc.createElement("etype"); 
                                String type_value = dataRelations[pos_ot2_1][j_ot2_1]; 
                                is_e_type.appendChild(doc.createTextNode(type_value)); 
                                is_type.appendChild(is_e_type);
                                
// Start / Property / DataType
                // ========   To find the "isDataType" position for property =====================
                pos = getTypePosition("isDataType");
                int pos_ot4_2 = 1000*pos;   int pos_ot4_2_end = 1000*pos+1000;
                Element is_d_type = doc.createElement("is-dtype"); 
                for (int i_ot4_2=pos_ot4_2+1; i_ot4_2<pos_ot4_2_end; i_ot4_2++) {
                    if (type_value.equals(dataRelations[i_ot4_2][pos_ot4_2])) {
                        for (int j_ot4_2=pos_ot4_2+1; j_ot4_2<pos_ot4_2_end; j_ot4_2++) {
                            if (dataRelations[i_ot4_2][j_ot4_2]==null) {continue;}
                            if (dataRelations[i_ot4_2][j_ot4_2].equals("X")) {
                                Element is_dtype = doc.createElement("dtype"); 
                                String dtype_value = dataRelations[pos_ot4_2][j_ot4_2]; 
                                is_dtype.appendChild(doc.createTextNode(dtype_value)); 
                                is_d_type.appendChild(is_dtype);
                            }
                        }
                    }
                }
                is_type.appendChild(is_d_type);
                // ========   To find the "isDataType" position for property =====================
// End / Property / DataType
// End / Property / EType / Property / EType
                // ========   To find the "isPropertyType" position for property =====================
                pos = getTypePosition("isPropertyType");
                int pos_ot6_2 = 1000*pos;   int pos_ot6_2_end = 1000*pos+1000;
                Element is_ee_type = doc.createElement("is-eetype"); 
                for (int i_ot6_2=pos_ot6_2+1; i_ot6_2<pos_ot6_2_end; i_ot6_2++) {
                    if (type_value.equals(dataRelations[i_ot6_2][pos_ot6_2])) {
                        for (int j_ot6_2=pos_ot6_2+1; j_ot6_2<pos_ot6_2_end; j_ot6_2++) {
                            if (dataRelations[i_ot6_2][j_ot6_2]==null) {continue;}
                            if (dataRelations[i_ot6_2][j_ot6_2].equals("X")) {
                                Element is_dtype = doc.createElement("dtype"); 
                                String dtype_value = dataRelations[pos_ot6_2][j_ot6_2];
//                                        typeMap(tList, dtype_value, 5);

                                is_dtype.appendChild(doc.createTextNode(dtype_value)); 
                                is_ee_type.appendChild(is_dtype);
                            }
                        }
                    }
                }
                is_type.appendChild(is_ee_type);
                // ========   To find the "isPropertyType" position for property =====================
// End / Property / EType / Property / EType
                                
                                is_etype_dtype.appendChild(is_type);

                            }
                        }
                    }
                }
                is_etype.appendChild(is_etype_dtype);
                // ========   To find the "isTypeProperty" position for property =====================
// End / Property / DataType




                                
                                is_data_etype.appendChild(is_etype);
                            }
                        }
                    }
                }
                is_property.appendChild(is_data_etype);
                // ========   To find the "isPropertyType" position for property =====================
// End / Property / PropertyType

   



                                hierarchy.appendChild(is_property);
                            }
                        }
                    }
                }
                // ========   To find the "isTypeProperty" position for types =====================

                
                rootElement.appendChild(hierarchy);
            }        

        // write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
                
	StreamResult result = new StreamResult(new File(fileTName));
	transformer.transform(source, result);
        JOptionPane.showMessageDialog(null, "File saved!");

	} catch (ParserConfigurationException | TransformerException pce) { }
    }
    
    
        
        // To create the answer graph
    // =========================================================================
    public void getByFieldsGraphXML(){
/*            
            for(int i=0; i<8000; i++)
                for(int j=0; j<8000; j++) {
                    if (dataRelations[i][j]==null) {continue;}
                    System.out.println("i="+i+" j= "+j+"  : "+dataRelations[i][j]);
                }
*/        
        int pos = 0;

        fileTName = pathTField+"ThingByFields.xml";
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("dictionary files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setDialogTitle("Choose a file to save of the Hierarchy of types"); 
        fileChooser.setSelectedFile(new File(fileTName));
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileTName = fileChooser.getSelectedFile().getAbsolutePath();
        }    

        try {
            DocumentBuilderFactory docTFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docTBuilder = docTFactory.newDocumentBuilder();
		Document doc = docTBuilder.newDocument();
		Element rootElement = doc.createElement("types");
		doc.appendChild(rootElement);
            
            String schema = null;    
            for(int i=0; i<hSchema.length; i++) {   
                schema = hSchema[i]; 
                // ========   To find the "isTypeProperty" position for types =====================
                pos = getTypePosition("isTypeProperty");
                int pos_ot2 = 1000*pos;   int pos_ot2_end = 1000*pos+1000;
                Element hierarchy = doc.createElement("is-type"); 
                    Element type_name = doc.createElement("type-name");
                    type_name.appendChild(doc.createTextNode(schema));
                    hierarchy.appendChild(type_name);
                for (int i_ot2=pos_ot2+1; i_ot2<pos_ot2_end; i_ot2++) {
                    if (schema.equals(dataRelations[i_ot2][pos_ot2])) {
                        for (int j_ot2=pos_ot2+1; j_ot2<pos_ot2_end; j_ot2++) {
                            if (dataRelations[i_ot2][j_ot2]==null) {continue;}
                            if (dataRelations[i_ot2][j_ot2].equals("X")) {
                                Element is_property = doc.createElement("is-property"); 
                                    Element prp_name = doc.createElement("property"); 
                                String property_value = dataRelations[pos_ot2][j_ot2]; 
                                prp_name.appendChild(doc.createTextNode(property_value)); 
                                is_property.appendChild(prp_name);
// Start / Property / DataType
                // ========   To find the "isDataType" position for property =====================
                pos = getTypePosition("isDataType");
                int pos_ot4 = 1000*pos;   int pos_ot4_end = 1000*pos+1000;
                Element is_data_type = doc.createElement("is-dtype"); 
                for (int i_ot4=pos_ot4+1; i_ot4<pos_ot4_end; i_ot4++) {
                    if (property_value.equals(dataRelations[i_ot4][pos_ot4])) {
                        for (int j_ot4=pos_ot4+1; j_ot4<pos_ot4_end; j_ot4++) {
                            if (dataRelations[i_ot4][j_ot4]==null) {continue;}
                            if (dataRelations[i_ot4][j_ot4].equals("X")) {
                                Element is_type = doc.createElement("dtype"); 
                                String type_value = dataRelations[pos_ot4][j_ot4]; 
                                is_type.appendChild(doc.createTextNode(type_value)); 
                                is_data_type.appendChild(is_type);
                            }
                        }
                    }
                }
                is_property.appendChild(is_data_type);
                // ========   To find the "isDataType" position for property =====================
// End / Property / DataType

// Start / Property / PropertyType
                // ========   To find the "isPropertyType" position for property =====================
                pos = getTypePosition("isPropertyType");
                int pos_ot6 = 1000*pos;   int pos_ot6_end = 1000*pos+1000;
                Element is_data_etype = doc.createElement("is-etype"); 
                for (int i_ot6=pos_ot6+1; i_ot6<pos_ot6_end; i_ot6++) {
                    if (property_value.equals(dataRelations[i_ot6][pos_ot6])) {
                        for (int j_ot6=pos_ot6+1; j_ot6<pos_ot6_end; j_ot6++) {
                            if (dataRelations[i_ot6][j_ot6]==null) {continue;}
                            if (dataRelations[i_ot6][j_ot6].equals("X")) {
                                Element is_etype = doc.createElement("etype"); 
                                    Element etype_name = doc.createElement("type-name"); 
                                String etype_value = dataRelations[pos_ot6][j_ot6]; 
                                etype_name.appendChild(doc.createTextNode(etype_value)); 
                                is_etype.appendChild(etype_name);
// Start / Property / PropertyType / DataType
                // ========   To find the "isTypeProperty" position for property =====================
                pos = getTypePosition("isTypeProperty");
                int pos_ot2_1 = 1000*pos;   int pos_ot2_1_end = 1000*pos+1000;
                Element is_etype_data = doc.createElement("is-type-property"); 
                for (int i_ot2_1=pos_ot2_1+1; i_ot2_1<pos_ot2_1_end; i_ot2_1++) {
                    if (etype_value.equals(dataRelations[i_ot2_1][pos_ot2_1])) {
                        for (int j_ot2_1=pos_ot2_1+1; j_ot2_1<pos_ot2_1_end; j_ot2_1++) {
                            if (dataRelations[i_ot2_1][j_ot2_1]==null) {continue;}
                            if (dataRelations[i_ot2_1][j_ot2_1].equals("X")) {
                                Element etype_property = doc.createElement("property"); 
                                    Element etype_prp_data = doc.createElement("prp-name"); 
                                String etype_dvalue = dataRelations[pos_ot2_1][j_ot2_1]; 
                                etype_prp_data.appendChild(doc.createTextNode(etype_dvalue)); 
                                etype_property.appendChild(etype_prp_data);
// Start / Property / DataType
                // ========   To find the "isDataType" position for property =====================
                pos = getTypePosition("isDataType");
                int pos_ot4_1 = 1000*pos;   int pos_ot4_1_end = 1000*pos+1000;
                Element is_etype_dtype = doc.createElement("is-dtype"); 
                for (int i_ot4_1=pos_ot4_1+1; i_ot4_1<pos_ot4_1_end; i_ot4_1++) {
                    if (property_value.equals(dataRelations[i_ot4_1][pos_ot4_1])) {
                        for (int j_ot4_1=pos_ot4_1+1; j_ot4_1<pos_ot4_1_end; j_ot4_1++) {
                            if (dataRelations[i_ot4_1][j_ot4_1]==null) {continue;}
                            if (dataRelations[i_ot4_1][j_ot4_1].equals("X")) {
                                Element is_type = doc.createElement("dtype"); 
                                String type_value = dataRelations[pos_ot4_1][j_ot4_1]; 
                                is_type.appendChild(doc.createTextNode(type_value)); 
                                is_etype_dtype.appendChild(is_type);
                            }
                        }
                    }
                }
                etype_property.appendChild(is_etype_dtype);
                // ========   To find the "isDataType" position for property =====================
// End / Property / DataType
                                
                                
                                is_etype_data.appendChild(etype_property);
                                
                            }
                        }
                    }
                }
                is_etype.appendChild(is_etype_data);
                // ========   To find the "isTypeProperty" position for property =====================
// End / Property / PropertyType / DataType

                                is_data_etype.appendChild(is_etype);
                            }
                        }
                    }
                }
                is_property.appendChild(is_data_etype);
                // ========   To find the "isPropertyType" position for property =====================
// End / Property / PropertyType

                                hierarchy.appendChild(is_property);
                            }
                        }
                    }
                }
                // ========   To find the "isTypeProperty" position for types =====================

                
                rootElement.appendChild(hierarchy);
            }        

        // write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
                
	StreamResult result = new StreamResult(new File(fileTName));
	transformer.transform(source, result);
        JOptionPane.showMessageDialog(null, "File saved!");

	} catch (ParserConfigurationException | TransformerException pce) { }
    }
    
    
    
    
        // To create the answer graph
    // =========================================================================
    public void getHierarchyTypeXML(){
/*            
            for(int i=0; i<8000; i++)
                for(int j=0; j<8000; j++) {
                    if (dataRelations[i][j]==null) {continue;}
                    System.out.println("i="+i+" j= "+j+"  : "+dataRelations[i][j]);
                }
*/        
        int pos = 0;

        fileTName = pathTField+"Thing.xml";
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("dictionary files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setDialogTitle("Choose a file to save of the Hierarchy of types"); 
        fileChooser.setSelectedFile(new File(fileTName));
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileTName = fileChooser.getSelectedFile().getAbsolutePath();
        }    

        try {
            DocumentBuilderFactory docTFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docTBuilder = docTFactory.newDocumentBuilder();
		Document doc = docTBuilder.newDocument();
		Element rootElement = doc.createElement("types");
		doc.appendChild(rootElement);
                
            // ==========  To choose list of types ========================
                pos = getTypePosition("isEntity");
                int pos_st0 = 1000*pos;    int pos_st0_end = 1000*pos+1000;
                    
                // Fill defined all types (entities)
                for (int i_st0=pos_st0+1; i_st0<pos_st0_end; i_st0++) {
                    Element is_type = doc.createElement("is-type"); 
                    for (int j_st0=pos_st0; j_st0<pos_st0_end; j_st0++) {
                        if (dataRelations[i_st0][j_st0]==null) {continue;}
                        if (j_st0==pos_st0) {
                            Element type_name = doc.createElement("type"); 
                            String type_value = dataRelations[i_st0][j_st0]; 
                                type_name.appendChild(doc.createTextNode(type_value)); 
                            is_type.appendChild(type_name);

//Part 1                                    
                        // ========   To find the "isEntity" position for types =====================
                            pos = getTypePosition("isEntity");
                            int pos_ot1 = 1000*pos;   int pos_ot1_end = 1000*pos+1000;
                            
                            Element hierarchy = doc.createElement("hierarchy"); 
                            // Fill the existed properties for types (entities)
                            for (int i_ot1=pos_ot1+1; i_ot1<pos_ot1_end; i_ot1++) {
                                if (type_value.equals(dataRelations[i_ot1][pos_ot1])) {
                                    for (int j_ot1=pos_ot1+1; j_ot1<pos_ot1_end; j_ot1++) {
                                        if (dataRelations[i_ot1][j_ot1]==null) {continue;}
                                        if (dataRelations[i_ot1][j_ot1].equals("X")) {
                                            Element path_name = doc.createElement("path"); 
                                            String path_value = dataRelations[pos_ot1][j_ot1]; 
                                            path_name.appendChild(doc.createTextNode(path_value)); 
                                            hierarchy.appendChild(path_name);
                                        }
                                    }
                                }
                            }
                            is_type.appendChild(hierarchy);
                        // ========   End of the "isEntity" position for rule  =====================
                            

//Part 1                                    
                        // ========   To find the "isTypeProperty" position for types =====================
                            pos = getTypePosition("isTypeProperty");
                            int pos_ot2 = 1000*pos;   int pos_ot2_end = 1000*pos+1000;
                            
                            Element properties = doc.createElement("properties"); 
                            // Fill the existed properties for types (entities)
                            for (int i_ot2=pos_ot2+1; i_ot2<pos_ot2_end; i_ot2++) {
                                if (type_value.equals(dataRelations[i_ot2][pos_ot2])) {
                                    for (int j_ot2=pos_ot2+1; j_ot2<pos_ot2_end; j_ot2++) {
                                        if (dataRelations[i_ot2][j_ot2]==null) {continue;}
                                        if (dataRelations[i_ot2][j_ot2].equals("X")) {
                                            Element is_property_name = doc.createElement("is-property"); 
                                                Element property_name = doc.createElement("property"); 
                                            String property_value = dataRelations[pos_ot2][j_ot2]; 
                                            property_name.appendChild(doc.createTextNode(property_value)); 
                                            is_property_name.appendChild(property_name);
                                            
//Part 1.1                                    
                                        // ========   To find the "isDataType" position for property =====================
                                            pos = getTypePosition("isDataType");
                                            int pos_ot4 = 1000*pos;   int pos_ot4_end = 1000*pos+1000;
                            
                                          //  Element is_data_type = doc.createElement("is-data-type"); 
                                            // Fill the existed values types for properties
                                            for (int i_ot4=pos_ot4+1; i_ot4<pos_ot4_end; i_ot4++) {
                                                if (property_value.equals(dataRelations[i_ot4][pos_ot4])) {
                                                    for (int j_ot4=pos_ot4+1; j_ot4<pos_ot4_end; j_ot4++) {
                                                        if (dataRelations[i_ot4][j_ot4]==null) {continue;}
                                                        if (dataRelations[i_ot4][j_ot4].equals("X")) {
                                                            Element data_types = doc.createElement("dtype"); 
                                                            String data_types_value = dataRelations[pos_ot4][j_ot4]; 
                                                            data_types.appendChild(doc.createTextNode(data_types_value)); 
                                                            is_property_name.appendChild(data_types);
                                            
                                                        }
                                                    }
                                                }
                                            }
//                                            is_property.appendChild(is_data_type);
                                        // ========   End of the "isDataType" position for property  =====================
                                        
                                        // ========   To find the "isPropertyType" position for property =====================
                                            pos = getTypePosition("isPropertyType");
                                            int pos_ot6 = 1000*pos;   int pos_ot6_end = 1000*pos+1000;
                            
 //                                           Element is_data_type = doc.createElement("is-data-type"); 
                                            // Fill the existed values types (entities) for properties
                                            for (int i_ot6=pos_ot6+1; i_ot6<pos_ot6_end; i_ot6++) {
                                                if (property_value.equals(dataRelations[i_ot6][pos_ot6])) {
                                                    for (int j_ot6=pos_ot6+1; j_ot6<pos_ot6_end; j_ot6++) {
                                                        if (dataRelations[i_ot6][j_ot6]==null) {continue;}
                                                        if (dataRelations[i_ot6][j_ot6].equals("X")) {
                                                            Element etype = doc.createElement("etype"); 
                                                            String etype_value = dataRelations[pos_ot6][j_ot6]; 
                                                            etype.appendChild(doc.createTextNode(etype_value)); 
                                                            is_property_name.appendChild(etype);
                                            
                                                        }
                                                    }
                                                }
                                            }
                                            properties.appendChild(is_property_name);
                                        // ========   End of the "isDataType" position for property  =====================
                                        
                                        }
                                    }
                                }
                            }
                            is_type.appendChild(properties);
                        // ========   End of the "isTypeProperty" position for rule  =====================

//Part 2                                    
                        // ========   To find the "isInheritanceOf" position for types =====================
                            pos = getTypePosition("isInheritanceOf");
                            int pos_ot3 = 1000*pos;   int pos_ot3_end = 1000*pos+1000;
                            
                            Element is_inheritance_from = doc.createElement("is-inheritance-from"); 
                            // Fill the existed properties for types (entities)
                            for (int i_ot3=pos_ot3+1; i_ot3<pos_ot3_end; i_ot3++) {
                                if (type_value.equals(dataRelations[i_ot3][pos_ot3])) {
                                    for (int j_ot3=pos_ot3+1; j_ot3<pos_ot3_end; j_ot3++) {
                                        if (dataRelations[i_ot3][j_ot3]==null) {continue;}
                                        if (dataRelations[i_ot3][j_ot3].equals("X")) {
                                            Element from_name = doc.createElement("from"); 
                                            String from_value = dataRelations[pos_ot3][j_ot3]; 
                                            from_name.appendChild(doc.createTextNode(from_value)); 
                                            is_inheritance_from.appendChild(from_name);
                                        }
                                    }
                                }
                            }
                            is_type.appendChild(is_inheritance_from);
                        // ========   End of the "isInheritanceOf" position for types  =====================
                        
//Part 3                                    
                        // ========   To find the "isInheritedIn" position for types =====================
                            pos = getTypePosition("isInheritedIn");
                            int pos_ot5 = 1000*pos;   int pos_ot5_end = 1000*pos+1000;
                            
                            Element is_inherited_in = doc.createElement("is-inherited-in"); 
                            // Fill the existed properties for types (entities) in types
                            for (int i_ot5=pos_ot5+1; i_ot5<pos_ot5_end; i_ot5++) {
                                if (type_value.equals(dataRelations[i_ot5][pos_ot5])) {
                                    for (int j_ot5=pos_ot5+1; j_ot5<pos_ot5_end; j_ot5++) {
                                        if (dataRelations[i_ot5][j_ot5]==null) {continue;}
                                        if (dataRelations[i_ot5][j_ot5].equals("X")) {
                                            Element is_in = doc.createElement("is-in"); 
                                            String is_in_value = dataRelations[pos_ot5][j_ot5]; 
                                            is_in.appendChild(doc.createTextNode(is_in_value)); 
                                            is_inherited_in.appendChild(is_in);
                                        }
                                    }
                                }
                            }
                            is_type.appendChild(is_inherited_in);
                        // ========   End of the "isInheritedIn" position for types  =====================
                        
                        }    
                        rootElement.appendChild(is_type);
                    }
                }   

        // write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
                
	StreamResult result = new StreamResult(new File(fileTName));
	transformer.transform(source, result);
        JOptionPane.showMessageDialog(null, "File saved!");

	} catch (ParserConfigurationException | TransformerException pce) { }
    }
    
    
    
}
/*
  public void getAllTables(javax.swing.JTabbedPane tabls){
        if(mt==null ){
            for(int i=0; i<qtb; i++) {
                mt = new TableTemplate();
                mt.getTableTemplate().getAccessibleContext().setAccessibleParent(tabls);
                tabls.getAccessibleContext().setAccessibleName("Table "+i);
                tabls.addTab("Table "+i, mt.getTableTemplate());
            }
        } else {
            for(int i=qtb+1; i<Integer.parseInt(qtableField.getText()); i++) {
                mt = new TableTemplate();
                mt.getTableTemplate().getAccessibleContext().setAccessibleParent(tabls);
                tabls.getAccessibleContext().setAccessibleName("Table "+i);
                tabls.addTab("Table "+i, mt.getTableTemplate());
            }
            qtb  = Integer.parseInt(qtableField.getText());
        }
    }

*/

/*
                              
                boolean exit = true;

                while (exit){
                    String[] prp = new String[50];
                    prp = getProperty(etype_value);
                    for(int i1=0; i1<prp.length; i1++){
                        if (prp[i1].equals("")) {continue;}
                            Element ctype = doc.createElement("cname"); 
                            ctype.appendChild(doc.createTextNode(prp[i1])); 
                            is_etype.appendChild(ctype);
                        
                    }
                    for(int ii = 0; ii<prp.length; ii++){
                        String[] val = new String[5];
                        val = getDataType(prp[ii]);
                    }
                    
                    for(int ii = 0; ii<prp.length; ii++){
                        String[] val = new String[5];
                        val = getComplexDataType(prp[ii]);
                    }
                    exit = false;
                }



*/






/*
        public static String[] hSchema = new String [] {
    "Thing", "Action", "AchieveAction", "LoseAction", "TieAction", "WinAction", "AssessAction",
    "ChooseAction", "VoteAction", "IgnoreAction", "ReactAction", "AgreeAction", "DisagreeAction",
    "DislikeAction", "EndorseAction", "LikeAction", "WantAction", "ReviewAction", "ConsumeAction",
    "DrinkAction", "EatAction", "InstallAction", "ListenAction", "ReadAction", "UseAction", "WearAction",
    "ViewAction", "WatchAction", "ControlAction", "ActivateAction", "DeactivateAction", "ResumeAction",
    "SuspendAction", "CreateAction", "CookAction", "DrawAction", "FilmAction", "PaintAction", "PhotographAction",
    "WriteAction", "FindAction", "CheckAction", "DiscoverAction", "TrackAction", "InteractAction",
    "BefriendAction", "CommunicateAction", "AskAction", "CheckInAction", "CheckOutAction", "CommentAction",
    "InformAction", "ConfirmAction", "RsvpAction", "InviteAction", "ReplyAction", "ShareAction", "FollowAction",
    "JoinAction", "LeaveAction", "MarryAction", "RegisterAction", "SubscribeAction", "UnRegisterAction", "MoveAction",
    "ArriveAction", "DepartAction", "TravelAction", "OrganizeAction", "AllocateAction", "AcceptAction", "AssignAction",
    "AuthorizeAction", "RejectAction", "ApplyAction", "BookmarkAction", "PlanAction", "CancelAction", "ReserveAction",
    "ScheduleAction", "PlayAction", "ExerciseAction", "PerformAction", "SearchAction", "TradeAction", "BuyAction",
    "DonateAction", "OrderAction", "PayAction", "PreOrderAction", "QuoteAction", "RentAction", "SellAction", "TipAction",
    "TransferAction", "BorrowAction", "DownloadAction", "GiveAction", "LendAction", "ReceiveAction", "ReturnAction",
    "SendAction", "TakeAction", "UpdateAction", "AddAction", "InsertAction", "AppendAction", "PrependAction", "DeleteAction",
    "ReplaceAction", "CreativeWork", "Article", "NewsArticle", "Report", "ScholarlyArticle", "MedicalScholarlyArticle",
    "SocialMediaPosting", "BlogPosting", "LiveBlogPosting", "DiscussionForumPosting", "TechArticle", "APIReference", "Atlas",
    "Blog", "Book", "Audiobook", "Chapter", "Clip", "MovieClip", "RadioClip", "TVClip", "VideoGameClip", "Collection", "ComicStory",
    "ComicCoverArt", "Comment", "Answer", "Conversation", "Course", "CreativeWorkSeason", "RadioSeason", "TVSeason", "CreativeWorkSeries",
    "BookSeries", "MovieSeries", "Periodical", "ComicSeries", "Newspaper", "RadioSeries", "TVSeries", "VideoGameSeries", "DataCatalog",
    "Dataset", "DataFeed", "Diet", "DigitalDocument", "NoteDigitalDocument", "PresentationDigitalDocument", "SpreadsheetDigitalDocument",
    "TextDigitalDocument", "EnumerationValueSet", "Episode", "RadioEpisode", "TVEpisode", "ExercisePlan", "Game", "VideoGame", "Map",
    "MediaObject", "AudioObject", "Audiobook", "DataDownload", "ImageObject", "Barcode", "MusicVideoObject", "VideoObject", "Message",
    "EmailMessage", "Movie", "MusicComposition", "MusicPlaylist", "MusicAlbum", "MusicRelease", "MusicRecording", "Painting", "Photograph",
    "PublicationIssue", "ComicIssue", "PublicationVolume", "Question", "Quotation", "Recipe", "Review", "ClaimReview", "Sculpture",
    "Series", "SoftwareApplication", "MobileApplication", "VideoGame", "WebApplication", "SoftwareSourceCode", "TVSeason", "TVSeries",
    "Thesis", "VisualArtwork", "CoverArt", "ComicCoverArt", "WebPage", "AboutPage", "CheckoutPage", "CollectionPage", "ImageGallery",
    "VideoGallery", "ContactPage", "ItemPage", "MedicalWebPage", "ProfilePage", "QAPage", "SearchResultsPage", "WebPageElement",
    "SiteNavigationElement", "Table", "WPAdBlock", "WPFooter", "WPHeader", "WPSideBar", "WebSite", "Event", "BusinessEvent", 
    "ChildrensEvent", "ComedyEvent", "CourseInstance", "DanceEvent", "DeliveryEvent", "EducationEvent", "EventSeries", "ExhibitionEvent",
    "Festival", "FoodEvent", "LiteraryEvent", "MusicEvent", "PublicationEvent", "BroadcastEvent", "OnDemandEvent", "SaleEvent",
    "ScreeningEvent", "SocialEvent", "SportsEvent", "TheaterEvent", "VisualArtsEvent", "Intangible", "AlignmentObject", "Audience",
    "BusinessAudience", "EducationalAudience", "MedicalAudience", "Patient", "PeopleAudience", "MedicalAudience", "ParentAudience",
    "BedDetails", "Brand", "BroadcastChannel", "RadioChannel", "TelevisionChannel", "BroadcastFrequencySpecification", "BusTrip", "Class",
    "ComputerLanguage", "DataFeedItem", "Demand", "DigitalDocumentPermission", "EntryPoint", "Enumeration", "ActionStatusType", 
    "BoardingPolicyType", "BookFormatType", "BusinessEntityType", "BusinessFunction", "ContactPointOption", "DayOfWeek", "DeliveryMethod",
    "LockerDelivery", "ParcelService", "DigitalDocumentPermissionType", "EnumerationValue", "EventStatusType", "GamePlayMode",
    "GameServerStatus", "GenderType", "ItemAvailability", "ItemListOrderType", "MapCategoryType", "MedicalEnumeration", "DrugClass",
    "DrugCost", "DrugCostCategory", "DrugPregnancyCategory", "DrugPrescriptionStatus", "InfectiousAgentClass", "MedicalAudience",
    "MedicalDevicePurpose", "MedicalEvidenceLevel", "MedicalImagingTechnique", "MedicalObservationalStudyDesign", "MedicalProcedureType",
    "MedicalSpecialty", "MedicalStudyStatus", "MedicalTrialDesign", "MedicineSystem", "PhysicalExam", "MedicalTrialDesign",
    "MusicAlbumProductionType", "MusicAlbumReleaseType", "MusicReleaseFormatType", "OfferItemCondition", "OrderStatus", "PaymentMethod",
    "PaymentCard", "CreditCard", "PaymentStatusType", "PhysicalActivityCategory", "QualitativeValue", "BedType", "CarUsageType",
    "DriveWheelConfigurationValue", "SteeringPositionValue", "ReservationStatusType", "RestrictedDiet", "RsvpResponseType", "Specialty",
    "MedicalSpecialty", "WarrantyScope", "Flight", "GameServer", "HealthInsurancePlan", "HealthPlanCostSharingSpecification",
    "HealthPlanFormulary", "HealthPlanNetwork", "Invoice", "ItemList", "BreadcrumbList", "OfferCatalog", "JobPosting", "Language",
    "ListItem", "Offer", "AggregateOffer", "Order", "OrderItem", "ParcelDelivery", "Permit", "GovernmentPermit", "ProgramMembership",
    "Property", "PropertyValueSpecification", "Quantity", "Distance", "Duration", "Energy", "Mass", "Rating", "AggregateRating",
    "Reservation", "BusReservation", "EventReservation", "FlightReservation", "FoodEstablishmentReservation", "LodgingReservation",
    "RentalCarReservation", "ReservationPackage", "TaxiReservation", "TrainReservation", "Role", "LinkRole", "OrganizationRole",
    "EmployeeRole", "PerformanceRole", "Seat", "Service", "BroadcastService", "CableOrSatelliteService", "FinancialProduct", "BankAccount",
    "DepositAccount", "CurrencyConversionService", "InvestmentOrDeposit", "DepositAccount", "LoanOrCredit", "CreditCard", "PaymentCard",
    "PaymentService", "FoodService", "GovernmentService", "TaxiService", "ServiceChannel", "StructuredValue", "ContactPoint", "PostalAddress",
    "EngineSpecification", "GeoCoordinates", "GeoShape", "GeoCircle", "InteractionCounter", "MonetaryAmount", "NutritionInformation",
    "OpeningHoursSpecification", "OwnershipInfo", "PriceSpecification", "CompoundPriceSpecification", "DeliveryChargeSpecification",
    "PaymentChargeSpecification", "UnitPriceSpecification", "PropertyValue", "LocationFeatureSpecification", "QuantitativeValue",
    "TypeAndQuantityNode", "WarrantyPromise", "Ticket", "TrainTrip", "MedicalEntity", "AnatomicalStructure", "Bone", "BrainStructure",
    "Joint", "Ligament", "Muscle", "Nerve", "Vessel", "Artery", "LymphaticVessel", "Vein", "AnatomicalSystem", "LifestyleModification",
    "Diet", "PhysicalActivity", "ExercisePlan", "MedicalCause", "MedicalCondition", "InfectiousDisease", "MedicalSignOrSymptom",
    "MedicalSign", "VitalSign", "MedicalSymptom", "MedicalContraindication", "MedicalDevice", "MedicalGuideline", "MedicalGuidelineContraindication",
    "MedicalGuidelineRecommendation", "MedicalIndication", "ApprovedIndication", "PreventionIndication", "TreatmentIndication",
    "MedicalIntangible", "DDxElement", "DoseSchedule", "MaximumDoseSchedule", "RecommendedDoseSchedule", "ReportedDoseSchedule",
    "DrugLegalStatus", "DrugStrength", "MedicalCode", "MedicalConditionStage", "MedicalProcedure", "DiagnosticProcedure", 
    "PalliativeProcedure", "PhysicalExam", "SurgicalProcedure", "TherapeuticProcedure", "MedicalTherapy", "OccupationalTherapy", "PalliativeProcedure",
    "PhysicalTherapy", "RadiationTherapy", "RespiratoryTherapy", "PsychologicalTreatment", "MedicalRiskEstimator", "MedicalRiskCalculator",
    "MedicalRiskScore", "MedicalRiskFactor", "MedicalStudy", "MedicalObservationalStudy", "MedicalTrial", "MedicalTest", "BloodTest", "ImagingTest",
    "MedicalTestPanel", "PathologyTest", "Substance", "DietarySupplement", "Drug", "SuperficialAnatomy", "Organization", "Airline",
    "Corporation", "EducationalOrganization", "CollegeOrUniversity", "ElementarySchool", "HighSchool", "MiddleSchool", "Preschool",
    "School", "GovernmentOrganization", "LocalBusiness", "AnimalShelter", "AutomotiveBusiness", "AutoBodyShop", "AutoDealer", "AutoPartsStore",
    "AutoRental", "AutoRepair", "AutoWash", "GasStation", "MotorcycleDealer", "MotorcycleRepair", "ChildCare", "Dentist", "DryCleaningOrLaundry",
    "EmergencyService", "FireStation", "Hospital", "PoliceStation", "EmploymentAgency", "EntertainmentBusiness", "AdultEntertainment",
    "AmusementPark", "ArtGallery", "Casino", "ComedyClub", "MovieTheater", "NightClub", "FinancialService", "AccountingService",
    "AutomatedTeller", "BankOrCreditUnion", "InsuranceAgency", "FoodEstablishment", "Bakery", "BarOrPub", "Brewery",
    "CafeOrCoffeeShop", "Distillery", "FastFoodRestaurant", "IceCreamShop", "Restaurant", "Winery", "GovernmentOffice", "PostOffice",
    "HealthAndBeautyBusiness", "BeautySalon", "DaySpa", "HairSalon", "HealthClub", "NailSalon", "TattooParlor", "HomeAndConstructionBusiness",
    "Electrician", "GeneralContractor", "HVACBusiness", "HousePainter", "Locksmith", "MovingCompany", "Plumber", "RoofingContractor",
    "InternetCafe", "LegalService", "Attorney", "Notary", "Library", "LodgingBusiness", "BedAndBreakfast", "Campground", "Hostel", "Hotel",
    "Motel", "Resort", "MedicalBusiness", "CommunityHealth", "Dentist", "Dermatology", "DietNutrition", "Emergency", "Geriatric", "Gynecologic",
    "MedicalClinic", "Midwifery", "Nursing", "Obstetric", "Oncologic", "Optician", "Optometric", "Otolaryngologic", "Pediatric", "Pharmacy",
    "Physician", "Physiotherapy", "PlasticSurgery", "Podiatric", "PrimaryCare", "Psychiatric", "PublicHealth", "ProfessionalService",
    "RadioStation", "RealEstateAgent", "RecyclingCenter", "SelfStorage", "ShoppingCenter", "SportsActivityLocation", "BowlingAlley",
    "ExerciseGym", "GolfCourse", "HealthClub", "PublicSwimmingPool", "SkiResort", "SportsClub", "StadiumOrArena", "TennisComplex", "Store",
    "AutoPartsStore", "BikeStore", "BookStore", "ClothingStore", "ComputerStore", "ConvenienceStore", "DepartmentStore", "ElectronicsStore",
    "Florist", "FurnitureStore", "GardenStore", "GroceryStore", "HardwareStore", "HobbyShop", "HomeGoodsStore", "JewelryStore", "LiquorStore",
    "MensClothingStore", "MobilePhoneStore", "MovieRentalStore", "MusicStore", "OfficeEquipmentStore", "OutletStore", "PawnShop", "PetStore",
    "ShoeStore", "SportingGoodsStore", "TireShop", "ToyStore", "WholesaleStore", "TelevisionStation", "TouristInformationCenter", "TravelAgency",
    "MedicalOrganization", "Dentist", "DiagnosticLab", "Hospital", "MedicalClinic", "Pharmacy", "Physician", "VeterinaryCare", "NGO",
    "PerformingGroup", "DanceGroup", "MusicGroup", "TheaterGroup", "SportsOrganization", "SportsTeam", "WorkersUnion", "Person", "Patient",
    "Place", "Accommodation", "Apartment", "CampingPitch", "House", "SingleFamilyResidence", "Room", "HotelRoom", "MeetingRoom", "Suite",
    "AdministrativeArea", "City", "Country", "State", "CivicStructure", "Airport", "Aquarium", "Beach", "Bridge", "BusStation", "BusStop",
    "Campground", "Cemetery", "Crematorium", "EventVenue", "FireStation", "GovernmentBuilding", "CityHall", "Courthouse", "DefenceEstablishment",
    "Embassy", "LegislativeBuilding", "Hospital", "MovieTheater", "Museum", "MusicVenue", "Park", "ParkingFacility", "PerformingArtsTheater",
    "PlaceOfWorship", "BuddhistTemple", "CatholicChurch", "Church", "HinduTemple", "Mosque", "Synagogue", "Playground", "PoliceStation",
    "RVPark", "StadiumOrArena", "SubwayStation", "TaxiStand", "TrainStation", "Zoo", "Landform", "BodyOfWater", "Canal", "LakeBodyOfWater",
    "OceanBodyOfWater", "Pond", "Reservoir", "RiverBodyOfWater", "SeaBodyOfWater", "Waterfall", "Continent", "Mountain", "Volcano",
    "LandmarksOrHistoricalBuildings", "LocalBusiness", "Residence", "ApartmentComplex", "GatedResidenceCommunity", "TouristAttraction",
    "Product", "IndividualProduct", "ProductModel", "SomeProducts", "Vehicle", "BusOrCoach", "Car", "Motorcycle", "MotorizedBicycle"
    };

*/