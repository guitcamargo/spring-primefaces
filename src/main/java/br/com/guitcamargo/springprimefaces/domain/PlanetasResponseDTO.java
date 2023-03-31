package br.com.guitcamargo.springprimefaces.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetasResponseDTO {
    private long count;
    private String next;
    private String previous;
    private List<PlanetasDTO> results;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PlanetasDTO> getResults() {
        return results;
    }

    public void setResults(List<PlanetasDTO> results) {
        this.results = results;
    }

    public boolean hasNextPage(){
        return StringUtils.isNotBlank(this.next);
    }
}

