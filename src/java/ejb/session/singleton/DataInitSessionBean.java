package ejb.session.singleton;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import ejb.session.stateless.ApplicationSessionBeanLocal;
import ejb.session.stateless.PositionTypeEntitySessionBeanLocal;
import ejb.session.stateless.IndustryEntitySessionBeanLocal;
import ejb.session.stateless.JobFunctionEntitySessionBeanLocal;
import ejb.session.stateless.JobEntitySessionBeanLocal;
import ejb.session.stateless.TagEntitySessionBeanLocal;
import entity.ApplicationEntity;
import entity.PositionTypeEntity;
import entity.IndustryEntity;
import entity.JobFunctionEntity;
import entity.JobEntity;
import entity.TagEntity;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.CreateNewApplicationException;
import util.exception.CreateNewPositionTypeException;
import util.exception.CreateNewIndustryException;
import util.exception.CreateNewJobFunctionException;
import util.exception.CreateNewJobException;
import util.exception.CreateNewTagException;
import util.exception.InputDataValidationException;
import util.exception.JobCompanyNameExistException;
import util.exception.UnknownPersistenceException;

@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager entityManager;

    @EJB
    private ApplicationSessionBeanLocal applicationSessionBeanLocal;
    @EJB
    private JobEntitySessionBeanLocal jobEntitySessionBeanLocal;
    @EJB
    private PositionTypeEntitySessionBeanLocal positionTypeEntitySessionBeanLocal;
    @EJB
    private IndustryEntitySessionBeanLocal industryEntitySessionBeanLocal;
    @EJB
    private JobFunctionEntitySessionBeanLocal jobFunctionEntitySessionBeanLocal;
    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;

    public DataInitSessionBean() {
    }

    @PostConstruct
    public void postConstruct() {
        initializeData();
    }

    private void initializeData() {
        try {
            PositionTypeEntity positionTypeEntityJobs = positionTypeEntitySessionBeanLocal.createNewPositionTypeEntity(new PositionTypeEntity("Jobs"), null);
            PositionTypeEntity positionTypeEntityInterns = positionTypeEntitySessionBeanLocal.createNewPositionTypeEntity(new PositionTypeEntity("Interns"), null);
            PositionTypeEntity positionTypeEntityFulltimeJobs = positionTypeEntitySessionBeanLocal.createNewPositionTypeEntity(new PositionTypeEntity("Full-time Job"), positionTypeEntityJobs.getPositionTypeId());
            PositionTypeEntity positionTypeEntityParttimeJobs = positionTypeEntitySessionBeanLocal.createNewPositionTypeEntity(new PositionTypeEntity("Part-time Job"), positionTypeEntityJobs.getPositionTypeId());
            PositionTypeEntity positionTypeEntityContractJobs = positionTypeEntitySessionBeanLocal.createNewPositionTypeEntity(new PositionTypeEntity("Contract Job"), positionTypeEntityJobs.getPositionTypeId());
            PositionTypeEntity positionTypeEntityInternships = positionTypeEntitySessionBeanLocal.createNewPositionTypeEntity(new PositionTypeEntity("Internship"), positionTypeEntityInterns.getPositionTypeId());

            IndustryEntity industryEntityJobs = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Jobs"), null);
            IndustryEntity industryEntityChemical = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Chemical, Energy and Resources"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityConsulting = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Consulting, Research and Services"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityConsumerGoods = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Consumer Goods (Electronics and Non-Electronics)"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityDistribution = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Distribution, Logistics and Supply Chain"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityEducation = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Education"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityEngineering = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Engineering and Manufacturing"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityFinancialServices = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Financial Services"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityInformationCommunicationsTechnology = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Information Communications Technology"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityLegalServices = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Legal Services"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityMedia = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Media and Entertainment"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityOthers = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Others"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityPharmaceutical = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Pharmaceutical, Healthcare, Biomedical Sciences"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityPublicSector = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Public Sector"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityRealEstate = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Real Estate and Construction"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityRetail = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Retail and Hospitality"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntitySocialServices = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Social Services, NGOs and IGOs"), industryEntityJobs.getIndustryId());
            IndustryEntity industryEntityTransport = industryEntitySessionBeanLocal.createNewIndustryEntity(new IndustryEntity("Transport, Maritime and Aerospace"), industryEntityJobs.getIndustryId());

            JobFunctionEntity jobFunctionEntityJobs = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Jobs"), null);
            JobFunctionEntity jobFunctionEntityAccounting = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Accounting and Bookkeeping"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityAdvertising = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Advertising"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityAgriculture = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Agriculture"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityArchitecture = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Architecture and Urban Planning"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityArts = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Arts/Design"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityClerical = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Clerical/Office Administration"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityConstruction = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Construction and Building Trades"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityConsulting = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Consulting"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityCustomerService = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Customer Service"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityEducation = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Education/Teaching"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityEngineering = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Engineering"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityEntertainment = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Entertainment/Performing Arts"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityEnvironment = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Environment/Natural Resources"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityFashionDesign = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Fashion Design/Modeling"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityFinancialServices = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Financial Services"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityHealthcare = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Healthcare"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityHospitality = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Hospitality"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityHumanResources = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Human Resources/Recruiting"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityInformationTechnology = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Information Technology"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityInsurance = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Insurance"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityLandscaping = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Landscaping and Pest Control"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityLawEnforcement = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Law Enforcement and Security"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityLegal = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Legal"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityManagement = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Management and Administration"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityManufacturing = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Manufacturing"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityOperations = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Operations/Supply Chain/Quality Assurance"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityOther = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Other"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityPublicRelations = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Public Relations/Communications"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityRealEstate = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Real Estate"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityReligion = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Religion/Pastoral"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityResearch = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Research and Development"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityRetail = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Retail"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntitySales = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Sales and Marketing"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityScience = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Science"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntitySocialServices = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Social Services"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntitySports = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Sports and Recreation"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityStatistics = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Statistics/Data Management"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityTransportation = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Transportation and Logistics"), jobFunctionEntityJobs.getJobFunctionId());
            JobFunctionEntity jobFunctionEntityWriting = jobFunctionEntitySessionBeanLocal.createNewJobFunctionEntity(new JobFunctionEntity("Writing & Editing/Journalism"), jobFunctionEntityJobs.getJobFunctionId());

            TagEntity tagEntityTrending = tagEntitySessionBeanLocal.createNewTagEntity(new TagEntity("TRENDING!"));
            TagEntity tagEntityExpiringSoon = tagEntitySessionBeanLocal.createNewTagEntity(new TagEntity("EXPIRING SOON!"));
            TagEntity tagEntityLatest = tagEntitySessionBeanLocal.createNewTagEntity(new TagEntity("LATEST!"));

            List<Long> tagIdsTrending = new ArrayList<>();
            tagIdsTrending.add(tagEntityTrending.getTagId());

            List<Long> tagIdsExpiringSoon = new ArrayList<>();
            tagIdsExpiringSoon.add(tagEntityExpiringSoon.getTagId());

            List<Long> tagIdsTrendingExpiringSoon = new ArrayList<>();
            tagIdsTrendingExpiringSoon.add(tagEntityTrending.getTagId());
            tagIdsTrendingExpiringSoon.add(tagEntityExpiringSoon.getTagId());

            List<Long> tagIdsTrendingLatest = new ArrayList<>();
            tagIdsTrendingLatest.add(tagEntityTrending.getTagId());
            tagIdsTrendingLatest.add(tagEntityLatest.getTagId());

            List<Long> tagIdsTrendingExpiringSoonLatest = new ArrayList<>();
            tagIdsTrendingExpiringSoonLatest.add(tagEntityTrending.getTagId());
            tagIdsTrendingExpiringSoonLatest.add(tagEntityExpiringSoon.getTagId());
            tagIdsTrendingExpiringSoonLatest.add(tagEntityLatest.getTagId());

            List<Long> tagIdsEmpty = new ArrayList<>();

            jobEntitySessionBeanLocal.createNewJob(new JobEntity("JobTitle1", "CompanyName1", "Location1", 10, "Description1", 2500), positionTypeEntityFulltimeJobs.getPositionTypeId(), industryEntityChemical.getIndustryId(), jobFunctionEntityAccounting.getJobFunctionId(), tagIdsTrending);
            jobEntitySessionBeanLocal.createNewJob(new JobEntity("JobTitle2", "CompanyName2", "Location2", 10, "Description2", 2500), positionTypeEntityParttimeJobs.getPositionTypeId(), industryEntityConsulting.getIndustryId(), jobFunctionEntityAdvertising.getJobFunctionId(), tagIdsExpiringSoon);
            jobEntitySessionBeanLocal.createNewJob(new JobEntity("JobTitle3", "CompanyName3", "Location3", 10, "Description3", 2500), positionTypeEntityContractJobs.getPositionTypeId(), industryEntityConsumerGoods.getIndustryId(), jobFunctionEntityAgriculture.getJobFunctionId(), tagIdsEmpty);
            applicationSessionBeanLocal.createNewApplication(new ApplicationEntity(0, "Shortlisted", "resume"));
            applicationSessionBeanLocal.createNewApplication(new ApplicationEntity(1, "Not Shortlisted", "resume"));
            applicationSessionBeanLocal.createNewApplication(new ApplicationEntity(2, "Pending", "resume"));

        } catch (JobCompanyNameExistException | UnknownPersistenceException | InputDataValidationException | CreateNewPositionTypeException | CreateNewIndustryException | CreateNewJobFunctionException | CreateNewTagException | CreateNewJobException ex) {
            ex.printStackTrace();
        }
    }
}
