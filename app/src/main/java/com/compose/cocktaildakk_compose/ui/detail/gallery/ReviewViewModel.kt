package com.compose.cocktaildakk_compose.ui.detail.gallery

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.compose.cocktaildakk_compose.data.data_source.GalleryPagingSource
import com.compose.cocktaildakk_compose.data.data_source.GalleryPagingSource.Companion.PAGING_SIZE
import com.compose.cocktaildakk_compose.domain.model.GalleryImage
import com.compose.cocktaildakk_compose.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : ViewModel() {

    private val _modifyingImage = mutableStateOf<GalleryImage?>(null)
    val modifyingImage: State<GalleryImage?> = _modifyingImage

    private val _selectedImages = mutableStateListOf<CroppingImage>()
    val selectedImages: SnapshotStateList<CroppingImage> = _selectedImages

    private val _selecetedStatus = mutableStateOf(ImageCropStatus.WAITING)
    val selecetedStatus: State<ImageCropStatus> = _selecetedStatus

    private val _rankScore = mutableStateOf(0)
    val rankScore: State<Int> get() = _rankScore

    val pagingCocktailList: Flow<PagingData<GalleryImage>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                initialLoadSize = 16,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GalleryPagingSource(imageRepository)
            }
        ).flow.cachedIn(viewModelScope)

    fun setRankScore(score: Int) {
        _rankScore.value = score
    }

    fun setCropStatus(status: ImageCropStatus) {
        _selecetedStatus.value = status
    }

    fun setModifyingImage(image: GalleryImage) {
        _modifyingImage.value = image
    }

    fun addSelectedImage(id: Long, image: Bitmap) {
        _selectedImages.add(CroppingImage(id, image))
    }

    fun deleteImage(id: Long) {
        val removedImage = _selectedImages.find { it.id == id }
        removedImage?.let {
            _selectedImages.remove(removedImage)
        }
    }

    fun addCropedImage(secondScreenResult: List<CroppingImage>?) {
        secondScreenResult?.let { _selectedImages.addAll(it) }
    }

    data class CroppingImage(
        val id: Long,
        val croppedBitmap: Bitmap
    )

    enum class ImageCropStatus {
        WAITING,
        MODIFYING,
        CROPPING;

        fun isModifying(action: () -> Unit) {
            if (this == MODIFYING) {
                action()
            }
        }

        fun isCropping(action: () -> Unit) {
            if (this == CROPPING) {
                action()
            }
        }
    }
}