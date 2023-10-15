package com.example.healthcarecenter;

public class request_medicine_model {
    String reuestUser_name,reuest_address,Request_email,rq_crocin_count,rq_paracetamol_count,rq_vicks_action_count,rq_bcomplex_count,rq_vitafol_count,rq_omee_count,req_other_medicine,req_other_medicine_count;

    public request_medicine_model() {
    }

    public request_medicine_model(String reuestUser_name, String reuest_address, String request_email, String rq_crocin_count, String rq_paracetamol_count, String rq_vicks_action_count, String rq_bcomplex_count, String rq_vitafol_count, String rq_omee_count, String req_other_medicine, String req_other_medicine_count) {
        this.reuestUser_name = reuestUser_name;
        this.reuest_address = reuest_address;
        this.Request_email = request_email;
        this.rq_crocin_count = rq_crocin_count;
        this.rq_paracetamol_count = rq_paracetamol_count;
        this.rq_vicks_action_count = rq_vicks_action_count;
        this.rq_bcomplex_count = rq_bcomplex_count;
        this.rq_vitafol_count = rq_vitafol_count;
        this.rq_omee_count = rq_omee_count;
        this.req_other_medicine = req_other_medicine;
        this.req_other_medicine_count = req_other_medicine_count;
    }

    public String getReuestUser_name() {
        return reuestUser_name;
    }

    public void setReuestUser_name(String reuestUser_name) {
        this.reuestUser_name = reuestUser_name;
    }

    public String getReuest_address() {
        return reuest_address;
    }

    public void setReuest_address(String reuest_address) {
        this.reuest_address = reuest_address;
    }

    public String getRequest_email() {
        return Request_email;
    }

    public void setRequest_email(String request_email) {
        this.Request_email = request_email;
    }

    public String getRq_crocin_count() {
        return rq_crocin_count;
    }

    public void setRq_crocin_count(String rq_crocin_count) {
        this.rq_crocin_count = rq_crocin_count;
    }

    public String getRq_paracetamol_count() {
        return rq_paracetamol_count;
    }

    public void setRq_paracetamol_count(String rq_paracetamol_count) {
        this.rq_paracetamol_count = rq_paracetamol_count;
    }

    public String getRq_vicks_action_count() {
        return rq_vicks_action_count;
    }

    public void setRq_vicks_action_count(String rq_vicks_action_count) {
        this.rq_vicks_action_count = rq_vicks_action_count;
    }

    public String getRq_bcomplex_count() {
        return rq_bcomplex_count;
    }

    public void setRq_bcomplex_count(String rq_bcomplex_count) {
        this.rq_bcomplex_count = rq_bcomplex_count;
    }

    public String getRq_vitafol_count() {
        return rq_vitafol_count;
    }

    public void setRq_vitafol_count(String rq_vitafol_count) {
        this.rq_vitafol_count = rq_vitafol_count;
    }

    public String getRq_omee_count() {
        return rq_omee_count;
    }

    public void setRq_omee_count(String rq_omee_count) {
        this.rq_omee_count = rq_omee_count;
    }

    public String getReq_other_medicine() {
        return req_other_medicine;
    }

    public void setReq_other_medicine(String req_other_medicine) {
        this.req_other_medicine = req_other_medicine;
    }

    public String getReq_other_medicine_count() {
        return req_other_medicine_count;
    }

    public void setReq_other_medicine_count(String req_other_medicine_count) {
        this.req_other_medicine_count = req_other_medicine_count;
    }
}
