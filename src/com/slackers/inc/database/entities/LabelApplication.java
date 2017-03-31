/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.DerbyConnection;
import com.slackers.inc.database.IEntity;
import java.io.IOException;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class LabelApplication implements IEntity{

    private static final String TABLE = "LABEL_APPLICATIONS";

    public static enum BeverageSource
    {
        DOMESTIC,
        IMPORTED;
    }    
    public static enum BeverageType
    {
        WINE,
        BEER,
        DISTILLED;
    }
    public static enum ApplicationStatus
    {
        SUBMITTED_FOR_REVIEW,
        REJECTED,
        APPROVED,
        SENT_FOR_CORRECTIONS;
    }
    public static enum RevisionType
    {
        CHANGE_NAME;
    }
    
    private long applicationId;
    private String representativeIdNumber;
    private String plantNumber;
    private BeverageSource productSource;
    private BeverageType productType;
    private String brandName;
    private Address applicantAddress;
    private Address mailingAddress;
    private String phoneNumber;
    private String emailAddress;
    private Date applicationDate;
    private ApplicationStatus status;
    private Manufacturer applicant;
    private UsEmployee reviewer;
    private UsEmployee submitter;
    private Label label;
    private List<LabelComment> comments;
    private ApplicationApproval applicationApproval;
    private Set<RevisionType> allowedRevisions;
    
    public LabelApplication(long applicationId)
    {
        this.allowedRevisions = new HashSet<>();
        this.applicant = null;
        this.applicantAddress = null;
        this.applicationApproval = null;
        this.applicationDate = new java.sql.Date(new java.util.Date().getTime());
        this.applicationId = applicationId;
        this.brandName = null;
        this.comments = new LinkedList<>();
        this.emailAddress = null;
        this.label = null;
        this.mailingAddress = null;
        this.phoneNumber = null;
        this.plantNumber = null;
        this.productSource = null;
        this.productType = null;
        this.representativeIdNumber = null;
        this.reviewer = null;
        this.status = null;
        this.submitter = null;
    }
    
    public LabelApplication()
    {
        this(0);
    }
    
    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public Map<String, Object> getEntityValues() {
        Map<String,Object> values = new HashMap<>();
        values.put("applicationId", this.applicationId);
        values.put("representativeIdNumber", this.representativeIdNumber);
        values.put("plantNumber", this.plantNumber);        
        values.put("productSource", this.productSource);
        values.put("productType", this.productType);
        values.put("brandName", this.brandName);        
        values.put("applicantAddress", this.applicantAddress);
        values.put("mailingAddress", this.mailingAddress);
        values.put("phoneNumber", this.phoneNumber);        
        values.put("emailAddress", this.emailAddress);
        values.put("applicationDate", this.applicationDate);
        values.put("status", this.status);        
        values.put("applicant", this.applicant.getPrimaryKeyValue());
        values.put("reviewer", this.reviewer.getPrimaryKeyValue());
        values.put("submitter", this.submitter.getPrimaryKeyValue());        
        values.put("label", this.label);
        values.put("comments", LabelComment.commentListToString(this.comments));
        values.put("applicationApproval", this.applicationApproval.getPrimaryKeyValue());        
        values.put("allowedRevisions", LabelApplication.allowedRevisionsToString(this.allowedRevisions));
        return values;
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String,Object> values = new HashMap<>();
        values.put("representativeIdNumber", this.representativeIdNumber);
        values.put("plantNumber", this.plantNumber);        
        values.put("productSource", this.productSource);
        values.put("productType", this.productType);
        values.put("brandName", this.brandName);        
        values.put("applicantAddress", this.applicantAddress);
        values.put("mailingAddress", this.mailingAddress);
        values.put("phoneNumber", this.phoneNumber);        
        values.put("emailAddress", this.emailAddress);
        values.put("applicationDate", this.applicationDate);
        values.put("status", this.status);        
        values.put("applicant", this.applicant.getPrimaryKeyValue());
        values.put("reviewer", this.reviewer.getPrimaryKeyValue());
        values.put("submitter", this.submitter.getPrimaryKeyValue());        
        values.put("label", this.label);
        values.put("comments", LabelComment.commentListToString(this.comments));
        values.put("applicationApproval", this.applicationApproval.getPrimaryKeyValue());        
        values.put("allowedRevisions", LabelApplication.allowedRevisionsToString(this.allowedRevisions));
        return values;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        if (values.containsKey("applicationId"))
        {
            this.applicationId = (long) values.get("applicationId");
        }
        if (values.containsKey("representativeIdNumber"))
        {
            this.representativeIdNumber = (String) values.get("representativeIdNumber");
        }
        if (values.containsKey("plantNumber"))
        {
            this.plantNumber = (String) values.get("plantNumber");
        }
        if (values.containsKey("productSource"))
        {
            this.productSource = (BeverageSource) values.get("productSource");
        }
        if (values.containsKey("productType"))
        {
            this.productType = (BeverageType) values.get("productType");
        }
        if (values.containsKey("brandName"))
        {
            this.brandName = (String) values.get("brandName");
        }
        if (values.containsKey("applicantAddress"))
        {
            this.applicantAddress = (Address) values.get("applicantAddress");
        }
        if (values.containsKey("mailingAddress"))
        {
            this.mailingAddress = (Address) values.get("mailingAddress");
        }
        if (values.containsKey("phoneNumber"))
        {
            this.phoneNumber = (String) values.get("phoneNumber");
        }
        if (values.containsKey("applicationDate"))
        {
            this.applicationDate = (Date) values.get("applicationDate");
        }
        if (values.containsKey("status"))
        {
            this.status = (ApplicationStatus) values.get("status");
        }
        if (values.containsKey("applicant"))
        {
            this.applicant.setPrimaryKeyValue((Serializable)values.get("applicant"));
            try {
                DerbyConnection.getInstance().getEntity(this.applicant, this.applicant.getPrimaryKeyName());
            } catch (SQLException ex) {
                Logger.getLogger(LabelApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (values.containsKey("reviewer"))
        {
            this.reviewer.setPrimaryKeyValue((Serializable)values.get("reviewer"));
            try {
                DerbyConnection.getInstance().getEntity(this.reviewer, this.reviewer.getPrimaryKeyName());
            } catch (SQLException ex) {
                Logger.getLogger(LabelApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (values.containsKey("submitter"))
        {
            this.submitter.setPrimaryKeyValue((Serializable)values.get("submitter"));
            try {
                DerbyConnection.getInstance().getEntity(this.submitter, this.submitter.getPrimaryKeyName());
            } catch (SQLException ex) {
                Logger.getLogger(LabelApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (values.containsKey("label"))
        {
            this.label = (Label) values.get("label");
        }
        if (values.containsKey("comments"))
        {
            this.comments = LabelComment.commentListFromString((String) values.get("comments"));
        }
        if (values.containsKey("applicationApproval"))
        {
            this.applicationApproval.setPrimaryKeyValue((Serializable)values.get("applicationApproval"));
            try {
                DerbyConnection.getInstance().getEntity(this.applicationApproval, this.applicationApproval.getPrimaryKeyName());
            } catch (SQLException ex) {
                Logger.getLogger(LabelApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (values.containsKey("allowedRevisions"))
        {
            this.allowedRevisions = LabelApplication.allowedRevisionsFromString((String)values.get("allowedRevisions"));
        }
    }

    @Override
    public Map<String, Class> getEntityNameTypePairs() {
        Map<String,Class> pairs = new HashMap<>();
        pairs.put("applicationId", Long.class);
        pairs.put("representativeIdNumber", String.class);
        pairs.put("plantNumber", String.class);        
        pairs.put("productSource", Serializable.class);
        pairs.put("productType", Serializable.class);
        pairs.put("brandName", String.class);        
        pairs.put("applicantAddress", Serializable.class);
        pairs.put("mailingAddress", Serializable.class);
        pairs.put("phoneNumber", String.class);        
        pairs.put("emailAddress", String.class);
        pairs.put("applicationDate", Date.class);
        pairs.put("status", Serializable.class);        
        pairs.put("applicant", Serializable.class);
        pairs.put("reviewer", Serializable.class);
        pairs.put("submitter", Serializable.class);        
        pairs.put("label", Serializable.class);
        pairs.put("comments", String.class);
        pairs.put("applicationApproval", Serializable.class);        
        pairs.put("allowedRevisions", String.class);
        return pairs;
    }

    @Override
    public List<String> tableColumnCreationSettings() {
        List<String> cols = new LinkedList<>();
        cols.add("applicationId bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)");
        cols.add("representativeIdNumber varchar(128)");
        cols.add("plantNumber varchar(128)");
        cols.add("productSource varchar(512)");
        cols.add("productType varchar(512)");
        cols.add("applicantAddress varchar(2048)");
        cols.add("mailingAddress varchar(2048)");
        cols.add("phoneNumber varchar(64)");
        cols.add("emailAddress varchar(128)");
        cols.add("applicationDate date");
        cols.add("status varchar(256)");
        cols.add("applicant varchar(256)");
        cols.add("reviewer varchar(256)");
        cols.add("label varchar(4096)");
        cols.add("comments varchar(max)");
        cols.add("applicationApproval varchar(128)");
        cols.add("allowedRevisions varchar(max)");
        return cols;
    }

    @Override
    public String getPrimaryKeyName() {
        return "applicationId";
    }
    
    @Override
    public Serializable getPrimaryKeyValue() {
        return this.applicationId;
    }

    @Override
    public void setPrimaryKeyValue(Serializable value) {}

    public void setPrimaryKeyValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getRepresentativeIdNumber() {
        return representativeIdNumber;
    }

    public void setRepresentativeIdNumber(String representativeIdNumber) {
        this.representativeIdNumber = representativeIdNumber;
    }

    public String getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    public BeverageSource getProductSource() {
        return productSource;
    }

    public void setProductSource(BeverageSource productSource) {
        this.productSource = productSource;
    }

    public BeverageType getProductType() {
        return productType;
    }

    public void setProductType(BeverageType productType) {
        this.productType = productType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Address getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(Address applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Manufacturer getApplicant() {
        return applicant;
    }

    public void setApplicant(Manufacturer applicant) {
        this.applicant = applicant;
    }

    public UsEmployee getReviewer() {
        return reviewer;
    }

    public void setReviewer(UsEmployee reviewer) {
        this.reviewer = reviewer;
    }

    public UsEmployee getSubmitter() {
        return submitter;
    }

    public void setSubmitter(UsEmployee submitter) {
        this.submitter = submitter;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public List<LabelComment> getComments() {
        return comments;
    }

    public void setComments(List<LabelComment> comments) {
        this.comments = comments;
    }

    public ApplicationApproval getApplicationApproval() {
        return applicationApproval;
    }

    public void setApplicationApproval(ApplicationApproval applicationApproval) {
        this.applicationApproval = applicationApproval;
    }

    public Set<RevisionType> getAllowedRevisions() {
        return allowedRevisions;
    }

    public void setAllowedRevisions(Set<RevisionType> allowedRevisions) {
        this.allowedRevisions = allowedRevisions;
    }

    
    public static String applicationListToString(List<LabelApplication> applications)
    {
        List<String> appIds = new LinkedList<>();
        for (LabelApplication e : applications)
        {
            try {
                appIds.add(DerbyConnection.objectToString((Serializable) e.getPrimaryKeyValue()));
            } catch (IOException ex) {
                Logger.getLogger(LabelApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return DerbyConnection.collectionToString(appIds);
    }
    
    public static List<LabelApplication> applicationListFromString(String appListString)
    {
        List<LabelApplication> applications = new LinkedList<>();
        List<String> appStrings = DerbyConnection.collectionFromString(appListString);
        for (String s : appStrings)
        {            
            try {
                LabelApplication temp = new LabelApplication();
                temp.setPrimaryKeyValue((Serializable) DerbyConnection.objectFromString(s));
                DerbyConnection.getInstance().getEntity(temp, temp.getPrimaryKeyName());
                applications.add(temp);
            } catch (Exception ex) {
                Logger.getLogger(LabelApplication.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return applications;
    }
    
    private static String allowedRevisionsToString(Set<RevisionType> allowedRevisions) {
        List<String> revs = new LinkedList<>();
        for (RevisionType e : allowedRevisions)
        {
            try {
                revs.add(DerbyConnection.objectToString(e));
            } catch (IOException ex) {
                Logger.getLogger(LabelApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return DerbyConnection.collectionToString(revs);
    }
    private static Set<RevisionType> allowedRevisionsFromString(String revString) {
        Set<RevisionType> allowedRevisions = new HashSet<>();
        List<String> revStrings = DerbyConnection.collectionFromString(revString);
        for (String s : revStrings)
        {            
            try {
                allowedRevisions.add((RevisionType)DerbyConnection.objectFromString(s));
            } catch (Exception ex) {
                Logger.getLogger(LabelApplication.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return allowedRevisions;
    }
}
