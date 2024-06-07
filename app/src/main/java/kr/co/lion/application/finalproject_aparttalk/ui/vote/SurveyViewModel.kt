import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.model.Survey
import kr.co.lion.application.finalproject_aparttalk.repository.SurveyRepository

class SurveyViewModel(private val repository: SurveyRepository) : ViewModel() {

    private val _saveResult = MutableLiveData<Boolean>()
    val saveResult: LiveData<Boolean> get() = _saveResult

    private val _surveys = MutableLiveData<List<Survey>>()
    val surveys: LiveData<List<Survey>> get() = _surveys

    fun saveSurvey(survey: Survey) {
        viewModelScope.launch {
            val result = repository.addSurvey(survey)
            _saveResult.postValue(result)
        }
    }

    fun fetchSurveys() {
        viewModelScope.launch {
            val surveyList = repository.getSurveys()
            _surveys.postValue(surveyList)
        }
    }

        suspend fun hasUserVoted(userId: String, surveyId: String): Boolean {
            return withContext(Dispatchers.IO) {
                repository.hasUserVoted(userId, surveyId)
            }
        }
    }



