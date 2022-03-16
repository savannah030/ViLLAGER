package com.savannah030.ViLLAGER.service;

import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.dto.MarkerResponseDto;
import com.savannah030.ViLLAGER.dto.OverlayResponseDto;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final BoardRepository boardRepository;

    public List<List<?>> findNearPlaces(Address address){
        List<List<?>> results = new ArrayList<>();
        List<Board> boardList = boardRepository.findAllByLatitudeAndLongitude(address.getLatitude(), address.getLongitude());
        //List<MarkerResponseDto> markerList = boardList.stream().map(MarkerResponseDto::new).collect(Collectors.toList());
        List<OverlayResponseDto> overlayList = boardList.stream().map(OverlayResponseDto::new).collect(Collectors.toList());
        //results.add(markerList);
        results.add(overlayList);
        return results;
    }



    /*
    @Transactional(readOnly = true)
    public JSONArray findNearPlacesJSON(){

        List<PlaceResponseDTO> DTOlist = boardRepository.findAllByLatitudeAndLongitude(37.48603206504228,126.98308494303069)
                .stream().map(PlaceResponseDTO::new).collect(Collectors.toList());

        //JSON Object --> { "":""}
        //JSON Array --> [ , , , ]

        JSONArray jsonArray = new JSONArray();

        for(PlaceResponseDTO dto: DTOlist){
            JSONObject jo = new JSONObject();
            jo.put("idx",dto.getIdx());
            jo.put("title",dto.getTitle());
            jo.put("content",dto.getContent());
            jo.put("latitude",dto.getLatitude());
            jo.put("longitude",dto.getLongitude());

            jsonArray.add(jo);
        }

        return jsonArray;
    }
    */

}
